
import org.gradle.api.JavaVersion.VERSION_1_8
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
	kotlin("jvm") version "1.3.21"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.3.21"
	id("org.springframework.boot") version "2.1.3.RELEASE"
	id("org.jetbrains.kotlin.plugin.spring") version "1.3.21"
}

apply(plugin="io.spring.dependency-management")

group = "de.javaclub"
version = "1.0.0-SNAPSHOT"

repositories {
	mavenCentral()
}

val developmentOnly by configurations.creating

configurations {
	runtimeClasspath {
		extendsFrom(configurations["developmentOnly"])
	}
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	implementation(kotlin("reflect"))

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.webjars:bootstrap:4.1.3")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.springframework.data:spring-data-jdbc")

	runtimeOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	testImplementation("org.junit.jupiter:junit-jupiter-api")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

	testImplementation("org.assertj:assertj-core")
	testImplementation("org.mockito:mockito-core")
	testImplementation("org.mockito:mockito-junit-jupiter")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
}

configure<JavaPluginConvention> {
	sourceCompatibility = VERSION_1_8
	targetCompatibility = VERSION_1_8
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
	kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
}

tasks.test {
	useJUnitPlatform()

	testLogging {
		lifecycle {
			events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
			exceptionFormat = TestExceptionFormat.FULL
			showExceptions = true
			showCauses = true
			showStackTraces = true
			showStandardStreams = true
		}
		info.events = lifecycle.events
		info.exceptionFormat = lifecycle.exceptionFormat
	}

	val failedTests = mutableListOf<TestDescriptor>()
	val skippedTests = mutableListOf<TestDescriptor>()

	// See https://github.com/gradle/kotlin-dsl/issues/836
	addTestListener(object : TestListener {
		override fun beforeSuite(suite: TestDescriptor) {}
		override fun beforeTest(testDescriptor: TestDescriptor) {}
		override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
			when (result.resultType) {
				TestResult.ResultType.FAILURE -> failedTests.add(testDescriptor)
				TestResult.ResultType.SKIPPED -> skippedTests.add(testDescriptor)
				else -> Unit
			}
		}

		override fun afterSuite(suite: TestDescriptor, result: TestResult) {
			if (suite.parent == null) { // root suite
				logger.lifecycle("----")
				logger.lifecycle("Test result: ${result.resultType}")
				logger.lifecycle(
						"Test summary: ${result.testCount} tests, " +
								"${result.successfulTestCount} succeeded, " +
								"${result.failedTestCount} failed, " +
								"${result.skippedTestCount} skipped")
				failedTests.takeIf { it.isNotEmpty() }?.prefixedSummary("\tFailed Tests")
				skippedTests.takeIf { it.isNotEmpty() }?.prefixedSummary("\tSkipped Tests:")
			}
		}

		private infix fun List<TestDescriptor>.prefixedSummary(subject: String) {
			logger.lifecycle(subject)
			forEach { test -> logger.lifecycle("\t\t${test.displayName()}") }
		}

		private fun TestDescriptor.displayName() = parent?.let { "${it.name} - $name" } ?: "$name"
	})
}
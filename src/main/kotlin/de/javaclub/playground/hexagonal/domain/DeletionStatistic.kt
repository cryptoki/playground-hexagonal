package de.javaclub.playground.hexagonal.domain

data class DeletionStatistic(
        val successful: List<DeletionProof>,
        val failure: List<DeletionProof>,
        val repaired: List<DeletionProof>)
package com.ryadamir.movieapp.util

fun convertDuration(duration: Int): String {
    val hours = duration / 60
    val minutes = duration % 60
    return String.format("%dh:%02dm", hours, minutes)
}

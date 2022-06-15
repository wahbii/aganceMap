package com.example.agancemap.models

data class InwiPosition(
    var agenceId: Long,
    var label: String,
    var district: Places,
    var city: Places,
    var addressAr: String,
    var addressFr: String,
    var filters: List<String>,
    var latitude: String,
    var logitude: String,
    var phone: List<String>,
    var schedule: List<Shulder>,
    var isDelevery: Boolean,
    var holiday: Boolean,
    var dayOff: Boolean


) {
}
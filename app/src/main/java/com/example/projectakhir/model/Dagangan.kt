package com.example.projectakhir.model

class Dagangan {
    var id: String? = ""
    private var namaDagangan: String = ""
    private var namaToko: String = ""
    private var deskripsiDagangan: String = ""
    private var hargaDagangan: String = ""
    private var AlamatToko: String = ""
    constructor():this("","","","","",""){}
    constructor(id: String?, namaDagangan: String, namaToko: String, deskripsiDagangan: String, AlamatToko:String, hargaDagangan: String){
        this.id = id
        this.namaDagangan = namaDagangan
        this.namaToko = namaToko
        this.deskripsiDagangan = deskripsiDagangan
        this.hargaDagangan = hargaDagangan
        this.AlamatToko = AlamatToko
    }

    fun getNamaDagangan(): String {
        return namaDagangan
    }
    fun setNamaDagangan(value: String) {
        namaDagangan = value
    }
    fun getNamaToko(): String {
        return namaToko
    }
    fun setNamaToko(value: String) {
        namaToko = value
    }
    fun getDeskripsiDagangan(): String {
        return deskripsiDagangan
    }
    fun setDeskripsiDagangan(value: String) {
        deskripsiDagangan = value
    }
    fun getHarga(): String {
        return hargaDagangan
    }
    fun setHarga(value: String) {
        hargaDagangan = value
    }
    fun getAlamatToko(): String {
        return AlamatToko
    }
    fun setAlamatToko(value: String) {
        AlamatToko = value
    }
}
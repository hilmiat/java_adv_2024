package FieldAnnotations;

// @PK -> jika di set element type field maka tidak dapat digunakan pada class
@Entity(table_name = "produk")
public class Product {
    @PK
    int id_Produk;

    // @Entity(table_name = "???") --> Jika di set target type maka hanya bisa dipakai untuk class
    String nama_produk;

    double harga;
}

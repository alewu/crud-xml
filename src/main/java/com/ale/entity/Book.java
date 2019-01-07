package com.ale.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;
@Data
public class Book {

    /**
     * title : Java Puzzlers: Traps, Pitfalls, and Corner Cases
     * isbn-10 : 032133678X
     * isbn-13 : 978-0321336781
     * authors : ["Joshua Bloch","Neal Gafter"]
     */

    private String title;
    @SerializedName("isbn-10")
    private String isbn10;
    @SerializedName("isbn-13")
    private String isbn13;
    private List<String> authors;


}

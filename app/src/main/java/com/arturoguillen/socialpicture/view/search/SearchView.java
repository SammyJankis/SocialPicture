package com.arturoguillen.socialpicture.view.search;

import java.util.List;

/**
 * Created by arturo.guillen on 12/09/2017.
 */

public interface SearchView {
    void searchOK(List<String> imagesUrls);

    void searchNOK(String s);

}

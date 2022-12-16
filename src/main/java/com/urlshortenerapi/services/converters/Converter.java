package com.urlshortenerapi.services.converters;

public interface Converter<DAO, Model> {
    Model fromDao(DAO dao);
    DAO toDao(Model model);
}

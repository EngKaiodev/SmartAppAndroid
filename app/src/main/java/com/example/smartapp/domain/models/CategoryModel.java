package com.example.smartapp.domain.models;

/**
 * Para criar um adapter, precisamos:
 * 1 - Criar um modelo com as informações -> Como CategoryModel
 * 2 - Criar um layout para o item genérico -> Como item_category (usar esse padrão de nomenclatura)
 * 3 - Criar um adapter -> Como CategoryAdapter
 * 4 - Substituir no layout, tudo oq está com o layout errado, por um recyclerView -> Como feito nas categorias em Dashboard.xml
 * 5 - Iniciar o recyclerView no fragment -> Como em dashboardFragment
 */
public class CategoryModel {
    private int id;
    private String name;

    public CategoryModel() {
    }

    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



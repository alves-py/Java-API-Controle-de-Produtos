package com.example.controledeprodutos.model.error;

public class ErrorMessage {
    private String title;
    private Integer status;
    private  String mensage;

    public ErrorMessage(String title, Integer status, String mensage) {
        this.title = title;
        this.status = status;
        this.mensage = mensage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensage() {
        return mensage;
    }

    public void setMensage(String mensage) {
        this.mensage = mensage;
    }
}

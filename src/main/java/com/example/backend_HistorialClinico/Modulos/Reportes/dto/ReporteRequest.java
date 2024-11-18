package com.example.backend_HistorialClinico.Modulos.Reportes.dto;

import java.util.List;
import java.util.Map;

public class ReporteRequest {
    private List<String> columnas;
    private Map<String, Object> filtros;
    private OrdenRequest orden;
    private String formato;
    
    public List<String> getColumnas() {
        return columnas;
    }
    public void setColumnas(List<String> columnas) {
        this.columnas = columnas;
    }
    public Map<String, Object> getFiltros() {
        return filtros;
    }
    public void setFiltros(Map<String, Object> filtros) {
        this.filtros = filtros;
    }
    public OrdenRequest getOrden() {
        return orden;
    }
    public void setOrden(OrdenRequest orden) {
        this.orden = orden;
    }
    public String getFormato() {
        return formato;
    }
    public void setFormato(String formato) {
        this.formato = formato;
    }

    // Getters y Setters
    // ...

    
}
// MedicoSpecifications.java
package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.specifications;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Medico;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Map;

public class MedicoSpecifications {

    public static Specification<Medico> crearEspecificaciones(Map<String, Object> filtros) {
        return (root, query, builder) -> {
            // Iniciamos con una condición verdadera
            Specification<Medico> spec = Specification.where(null);

            if (filtros == null || filtros.isEmpty()) {
                return spec.toPredicate(root, query, builder);
            }

            // Iterar sobre los filtros y construir las condiciones
            for (Map.Entry<String, Object> filtro : filtros.entrySet()) {
                String campo = filtro.getKey();
                Object valor = filtro.getValue();

                switch (campo) {
                    case "fecha contratacion":
                        LocalDate fechaContratacion = LocalDate.parse(valor.toString());
                        spec = spec.and((root1, query1, builder1) -> builder1.equal(root1.get("fechaContratacion"),
                                fechaContratacion));
                        break;
                    case "estado":
                        spec = spec.and(
                                (root1, query1, builder1) -> builder1.equal(root1.get("estado"), valor.toString()));
                        break;
                    case "genero":
                        spec = spec.and((root1, query1, builder1) -> builder1.equal(root1.get("user").get("genero"),
                                valor.toString()));
                        break;
                    case "fecha_contratacion_desde":
                        LocalDate fechaDesde = LocalDate.parse(valor.toString());
                        spec = spec.and((root1, query1, builder1) -> builder1
                                .greaterThanOrEqualTo(root1.get("fechaContratacion"), fechaDesde));
                        break;
                    case "fecha_contratacion_hasta":
                        LocalDate fechaHasta = LocalDate.parse(valor.toString());
                        spec = spec.and((root1, query1, builder1) -> builder1
                                .lessThanOrEqualTo(root1.get("fechaContratacion"), fechaHasta));
                        break;
                    case "especialidad":
                        spec = spec.and((root1, query1, builder1) -> {
                            Join<Object, Object> especialidades = root1.join("especialidades", JoinType.LEFT);
                            return builder1.equal(especialidades.get("nombre"), valor.toString());
                        });
                        break;
                    default:
                        break;

                }
            }

            return spec.toPredicate(root, query, builder);
        };
    }
}

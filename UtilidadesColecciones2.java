package cafeteria;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UtilidadesColecciones {

    @SafeVarargs
    public static <T> List<T> crearColeccionGenerica(T... elementos) {
        List<T> lista = new ArrayList<>();
        for (T e : elementos) {
            lista.add(e);
        }
        return lista;
    }

    @SafeVarargs
    public static <T> void agregarVarios(List<T> lista, T... elementos) {
        for (T e : elementos) {
            lista.add(e);
        }
    }

    public static <T> List<T> filtrarElementos(List<T> lista, Predicate<T> criterio) {
        List<T> resultado = new ArrayList<>();
        for (T e : lista) {
            if (criterio.test(e)) {
                resultado.add(e);
            }
        }
        return resultado;
    }
}
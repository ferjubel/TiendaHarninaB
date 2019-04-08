package procedures;

public enum ProceduresProductos {
    GET_MODELOS("getPhoneModels"),
    GET_CARRITO("getCarrito"),
    GUARDAR_CARRITO("guardarCarrito"),
    DELETE_CARRITO("deleteCarrito"),
    GET_FACTURAS_CABECERAS("getFacturasCabeceras"),
    GET_FACTURAS_DETALLES("getFacturasDetalles"),
    COMPRAR_CARRITO("hacerFactura");

    private final String name;

    ProceduresProductos(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}


//CODIGO PARA CREAR VISTAS
//CREATE VIEW jspageview as SELECT pagina.page.pageName
// as pageName, pagina.filejs.pathFileJs as
// path,pagina.filejs.fileJs,pagina.pagejs.orderFileJs
// FROM pagina.page,pagina.pagejs,pagina.filejs
// WHERE pagina.filejs.idFileJs = pagina.pagejs.IdFileJs
// AND pagina.page.codePage = pagina.pagejs.idPage
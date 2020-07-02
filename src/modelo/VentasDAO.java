package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentasDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    
    public String NroSerieVentas(){
        String serie = "";
        String sql = "select max(NumeroSerie) from ventas";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                serie = rs.getString(1);
            }
        } catch (Exception e) {
        }
        
        return serie;
    }
    
    public String IdVentas(){
        String idv = "";
        String sql = "select max(IdVentas) from ventas";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                idv = rs.getString(1);
            }
        } catch (Exception e) {
        }
        return idv;
    }
    
    public int GuardarVentas(Ventas v){
        String sql = "insert into Ventas(IdCliente, IdVendedor,NumeroSerie,FechaVentas,Monto,Estado)values(?,?,?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, v.idCliente);
            ps.setInt(2, v.idVendedor);
            ps.setString(3, v.serie);
            ps.setString(4, v.Fecha);
            ps.setDouble(5, v.monto);
            ps.setString(6, v.estado);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error: "+e);
        }
        
        return r;
    }
    
    public int GuardarDetalleVentas(DetalleVentas dv){
        String sql = "insert into detalle_ventas(IdVentas,IdProducto,Cantidad,PrecioVenta)values(?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dv.getIdVentas());
            ps.setInt(2, dv.getIdProducto());
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getPrecioVenta());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
}

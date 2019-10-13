package com.systig.systigmaster.inventario.controladores;

import com.google.gson.Gson;
import com.systig.systigmaster.inventario.modelos.Producto;
import com.systig.systigmaster.inventario.modelos.UsuarioActivo;
import com.systig.systigmaster.inventario.servicios.interfaces.IProductosServ;
import com.systig.systigmaster.inventario.servicios.interfaces.IUsuarioServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductosCtrl {

    private final IProductosServ productosServ;

    private final IUsuarioServ usuarioServ;

    public ProductosCtrl(IProductosServ productosServ, IUsuarioServ usuarioServ) {
        this.productosServ = productosServ;
        this.usuarioServ = usuarioServ;
    }

    @GetMapping("/api/login")
    public String login(Principal principal, HttpServletRequest requests,HttpSession session) {
        String tokenEnc = this.usuarioServ.getTokenSession(principal, requests, session);
        UsuarioActivo tokenDesc = this.usuarioServ.getUsuarioToken(tokenEnc);
        System.out.println("TokenInicial --> " + tokenEnc);
        System.out.println("UsuarioToken --> " + (new Gson()).toJson(tokenDesc));
        return tokenEnc;
    }

    @GetMapping("/api/productos")
    public ResponseEntity<?> getListaProductos(@RequestHeader HttpHeaders headers, HttpSession session) {
        try{
            String token =  String.valueOf(headers.get("TokenSystig")).replace("[","").replace("]","");
            UsuarioActivo usuarioActivo = this.usuarioServ.getUsuarioToken(token);
            if(usuarioActivo.getSessionId().equals(session.getId())){
                return new ResponseEntity<>(this.productosServ.getListadoLigero(usuarioActivo), HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/api/producto/{id_producto}")
    public ResponseEntity<?> getProducto(@RequestHeader HttpHeaders headers, HttpSession session,
                                                @PathVariable Long id_producto) {
        try{
            String token =  String.valueOf(headers.get("TokenSystig")).replace("[","").replace("]","");
            UsuarioActivo usuarioActivo = this.usuarioServ.getUsuarioToken(token);
            if(usuarioActivo.getSessionId().equals(session.getId())){
                return new ResponseEntity<>(this.productosServ.getProducto(usuarioActivo,id_producto), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Producto(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new Producto(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/api/producto")
    public ResponseEntity<?> agregarProducto(@RequestHeader HttpHeaders headers, HttpSession session,
                                                @RequestBody Producto producto) {
        try{
            String token =  String.valueOf(headers.get("TokenSystig")).replace("[","").replace("]","");
            UsuarioActivo usuarioActivo = this.usuarioServ.getUsuarioToken(token);
            if(usuarioActivo.getSessionId().equals(session.getId())){
                this.productosServ.nuevoProducto(usuarioActivo, producto);
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
            return new ResponseEntity<>("FALLIDO", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FALLIDO", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/api/producto")
    public ResponseEntity<?> actualizarProducto(@RequestHeader HttpHeaders headers, HttpSession session,
                                                  @RequestBody Producto producto) {
        try{
            String token =  String.valueOf(headers.get("TokenSystig")).replace("[","").replace("]","");
            UsuarioActivo usuarioActivo = this.usuarioServ.getUsuarioToken(token);
            if(usuarioActivo.getSessionId().equals(session.getId())){
                this.productosServ.actualizarProducto(usuarioActivo, producto);
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
            return new ResponseEntity<>("FALLIDO", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FALLIDO", HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/api/producto/{id_producto}")
    public ResponseEntity<?> borrarProducto(@RequestHeader HttpHeaders headers, HttpSession session,
                                                     @PathVariable Long id_producto) {
        try{
            String token =  String.valueOf(headers.get("TokenSystig")).replace("[","").replace("]","");
            UsuarioActivo usuarioActivo = this.usuarioServ.getUsuarioToken(token);
            if(usuarioActivo.getSessionId().equals(session.getId())){
                this.productosServ.borrarProducto(usuarioActivo, id_producto);
                return new ResponseEntity<>("OK", HttpStatus.OK);
            }
            return new ResponseEntity<>("FALLIDO", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("FALLIDO", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/api/producto/historia/{id_producto}")
    public ResponseEntity<?> getHistoriaProducto(@RequestHeader HttpHeaders headers, HttpSession session,
                                                        @PathVariable Long id_producto) {
        try{
            String token =  String.valueOf(headers.get("TokenSystig")).replace("[","").replace("]","");
            UsuarioActivo usuarioActivo = this.usuarioServ.getUsuarioToken(token);
            if(usuarioActivo.getSessionId().equals(session.getId())){
                return new ResponseEntity<>(this.productosServ.getHistoriaProducto(id_producto), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/api/producto/importar", consumes = {"multipart/form-data"})
    public ResponseEntity<?> importarArchivo(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) throws IOException {
        return new ResponseEntity<>(file.getName(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/producto/exportar/XML", produces = { "application/xml", "text/xml" })
    public ResponseEntity exportarXML() {
        return null;
    }

}

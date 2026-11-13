# Sistema de Gestion de Biblioteca (POO)

**Estudiante:** Franklyn Daniel Blanco Alcantara  
**Numero de Cuenta:** 202310010572  
**Clase:** Programacion Orientada a Objetos (UTH)  

---

##  Instrucciones para Ejecutar
1. Clonar el repositorio o abrir el proyecto en **NetBeans IDE** con soporte para Maven.
2. Asegurarse de que el archivo `pom.xml` contenga la dependencia del conector JDBC de SQLite.
3. Ejecutar la clase principal ubicada en:
   `hn.uth.poo.biblioteca.app.MainApp` (o la clase de menu/interfaz principal).
4. La base de datos `biblioteca.db` se generara automaticamente en la raiz del proyecto al iniciar la ejecucion.

---

##  Evidencias de Ejecucion

### 1. Operaciones CRUD - Entidad Autor
*Insercion de autores.*  
![CRUD Autores](evidencias/codigo_crud_insertar.png)

### 2. Operaciones CRUD completa - Ejecucion de codigo  
![CRUD todos](evidencias/ejecucion_exitosa.png)


### 3. Reglas de Negocio y Manejo de Excepciones

* **Regla 1: Restriccion de ISBN Repetido**  
  El sistema valida la unicidad del ISBN lanzando una excepcion de tipo `DatosException` al intentar registrar un libro duplicado.  
  ![ISBN Repetido](error_regla_negocio.png)


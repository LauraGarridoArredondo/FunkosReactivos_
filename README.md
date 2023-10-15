# FunkosReactivos_
<h1>Hecho por Miguel Zanotto y Laura Garrido</h1>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/03b9d305-260e-4e02-a457-ab7cb7da7d68)
<p>Esta es la estructura de nuestro repositorio</p>
<del>Aunque se llame el repositorio FunkosAsync es FunkoReactivos.</del>
<del>Y la carpeta .idea de fuera de FunkoAsync fue un error subirlo.</del>
<br>
<h1>Carpeta Develop</h1>
<H2>Carpeta controller</H2>
<p>Esta carpeta contiene las clases:</p>
<ul>
  <li><b>CSVReader</b>
  <p>Esta clase se encarga de leer los datos del fichero de funkos en la carpeta de data de forma reactiva con Flux y junto con las opciones getUUID (Conseguir el UUID del funko), getDate (Conseguir la fecha con el formato yyyy-MM-dd) y GetFunko en ella.</p>
    
  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/8f12afe4-5332-44d7-9f20-3d6e8c1dc2b0)
  </li>
  <li><b>ExportJSON</b>
  <p>Esta clase se encarga de crear el fichero json de forma Reactiva con Mono de Funkos, importando y exportando el fichero JSON</p>
  <u>Un scheduler es el que se encarga de asignar tiempo de CPU a los hilos en ejecución. </u>
  
  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/422568c0-317a-4f40-b2df-fc77a21d6dee)
  </li>
</ul>

<h2>Carpeta exceptions.funko</h2>
<p>Esta carpeta contiene las excepciones de Funko para mostrar un mensaje para cada una de excepcion que se pueda dar.</p>
<p>Hemos creado las excepciones:</p>

<ul>
<li><b>FunkoExceptio</b>
<p>Esta muestra un mensaje si hay una excepcion en la clase Funko</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/8d2ea1d7-f451-4e81-9395-a8439f81ddd8)

</li>
  
<li><b>FunkoNoAlmacenadoException</b>
<p>En esta se muestra un mensaje si no se ha podido almacenar bien los funkos</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/79947ab1-6997-4d14-bd0a-c8c8e24fcfd5)

</li>
  
<li><b>FunkoNoEncontrado</b>
<p>En esta se muestra un mensaje si no se ha podido encontrar el Funko.</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/546bd100-4628-4179-a5b7-0e79ea5473ef)
</li>
</ul>

<h2>Carpeta Locale</h2>
<p>Esta carpeta esta encargada de con la clase MyLocale para formatear fechas y cantidades de dinero de acuerdo con la configuración regional y el idioma de España</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/4320eceb-e9f4-4868-aad7-a67ce1ffecda)

<h2>Carpeta Models</h2>
<P>Esta carpeta tiene los modelos del cual se utilizaran en los repositorios y servicios.</P>
<ul>
  <li><b>Funko</b>
  <p>Aqui se muestran las propiedades de Funko</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/bf4d151a-a3cc-44b3-af22-3d1d513492eb)
  </li>
  
  <li><b>IdGenerator</b>
  <p>Aqui se muestra el generador de Ids de forma reactiva con Mono.</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/a553f94a-9715-4c0b-8378-f86928c701c7)
  </li>
  
  <li><b>Model</b>
  <p>Aqui se muestra los modelos de cada Funko como estan separados</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/407d7a8f-e618-4139-a81a-a0d468ddcb97)
  </li>
</ul>

<h2>Carpeta repositories</h2>
<p>Aqui se encuentran las interfaces y clases para poder hacer las funcionalidades Crud de forma reactiva</p>
<ul>
<li><b>Carpeta crud</b>
<p>Aqui se encuentra la interfaz de CrudRepository, donde se tomara en cuenta la interfaz planteada de forma reactiva con Mono y Flux.</p>

  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/1ead1f3b-f641-4b7c-87ef-2a8649dde6e3)
</li>

<li><b>Carpeta funkos</b>
<p>Aqui se encuentra la interfaz FunkosRepositories y la clase FunkosRepositoryImpl donde se realizaran las operaciones de Crud y findByNombre,FindAllByNombre.</p>

 ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/bef147ee-09e9-47f3-8ab5-006f2c14dc98)
 ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/095e6682-c84b-4150-92fa-9335dd06d7ce)
 ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/71b99f5d-8918-4deb-a8fd-1f05e5814dbe)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/e58b55ee-f2b0-4f64-9f39-0464a17cd553)

</li>
</ul>

<h2>Carpeta Services</h2>
<p>Esta carpeta se puede encontrar las subcarpetas cache, database y funkos, donde se creara el cache de funkos, el manejador de datos de la base de datos de Funkos, los Servicios de Funkos y las Notificaciones de Funkos.</p>
<ul>
  <li><b>Carpeta Cache</b>
  <p>Aqui se encuentra la interfaz del cache de forma reactiva</p>
    
  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/5a2c67a4-a627-42c6-bf7c-898bc5d51618)
  </li>

  <li><b>Carpeta Database</b>
  <p>Aqui se encuentra la clase DataBaseManager para poder utilizar la base de datos de Funkos.</p>

  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/188e9948-c756-4173-9d67-af5a48f0aa68)
  </li>

  <li><b>Carpeta funkos</b>
  <p>Aqui se crean los servicios de cache, notificacion y Servicios de Funkos</p>
    <b>Funko cache interfaz:</b>

  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/4aa35a9b-5a01-4a3b-bdd7-cf3829fc6c53)
  <br>  <b>FunkoCacheImpl</b> <p>Esto se encarga de realizar los servicios de la interfaz de la cache de Funko.</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/f7036c91-b27f-484e-be90-4f52c39cb54c)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/2a23d155-4696-49b8-9696-c3f12b782ac8)

<br> <b>FunkoRepositoryConnectable</b> <p>Aqui se ceran las conexiones para utlizarse en las notificaciones de Funko</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/cbe6b914-1b8d-490c-859d-66827b6665ad)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/4664fdd0-3c60-4a57-bade-e61aa360cb21)

<br> <b>FunkoRepositoryFluxSink</b> <p>Esta clase proporciona una forma reactiva de gestionar una colección de figuras Funko, permitiendo a los clientes recibir notificaciones cuando se agregan o eliminan figuras, así como notificaciones generales.</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/29ccaeaa-3632-4cff-9521-012b29e5fe5e)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/1c5d99bf-c1ce-4b23-8d9b-9bbe04e1dc3f)

<br> <b>FunkoService interfaz</b> <p>Esta interfaz define un conjunto de métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en Funko de forma reactiva con Mono y Flux.</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/2245e15b-bedc-4518-a0e2-1a124f47043f)

<br> <b>FunkoServiceImpl</b> <p>Es una implementación de la interfaz FunkosRepository que actúa como una capa de servicio para operaciones relacionadas con figuras Funko. Además, mantiene una caché de figuras Funko para mejorar el rendimiento y reducir las consultas a la fuente de datos subyacente.</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/b64162c2-e2b7-4b54-9efa-0d7ba1264ba9)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/9e05763f-7d5f-464a-850e-f1114c20b736)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/5567d609-d140-45dc-88e1-93139cac593d)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/b036a8d1-bc43-45ea-9d4d-d109c15a0e70)


  </li>
</ul>

<h2>Clase Main</h2>
<p>Aqui esta el main del cual se encargara de realizar las notificaciones y hacer consultas a Funkos (API)</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/e64785bb-eb72-4894-87ca-e517bd776a22)
<P>NOTFICACIONES:</P>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/7e38ad32-b6e5-4670-815f-a5c7b2f48f38)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/806971b7-3cef-4901-bbde-67e2b6e3f55a)
<P>Comienzo consultas a Funkos</P>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/a48bbddf-e5f5-4f8a-b3b3-02b060081884)

<h2>Carpeta Resources</h2>
<p>Aqui dentro se encuentra las propiedades de la base de datos de Funkos con sus propiedades configuradas para H2 con R2DBC</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/74fa7ee6-fe43-4faf-9868-70d284606a81)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/7b0052af-7e6f-4219-b682-530b822f09c7)

<h2>Carpeta test</h2>
<p>He aqui los test necesarios para los servicios de Funkos.</p><

<h1>DEPENDENCIAS UTILIZADAS</h1>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/625e6e04-1536-4435-b3ea-b897f82159ea)


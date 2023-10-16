# FunkosReactivos_
<h1>Hecho por Miguel Zanotto y Laura Garrido</h1>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/eb0bc24e-0e34-40b5-9a22-9fe2f2727cf0)
<p>Esta es la estructura de nuestro repositorio</p>
<del>Aunque se llame el repositorio FunkosAsync es FunkoReactivos.</del>
<br>
<h1>Carpeta Develop</h1>

<h2>Carpeta exceptions / funkos</h2>
<p>Esta carpeta contiene las excepciones de Funko para mostrar un mensaje para cada una de excepcion que se pueda dar.</p>
<p>Hemos creado las excepciones:</p>

<ul>
<li><b>FunkoException</b>
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

<h2>Carpeta storage</h2>
<p>Esta carpeta contiene las excepciones que se den dentro de la base de datos.</p>
<ul>
  <li><b>RunInvalidaException</b>
  <p>Aqui se representa una excepcion relacionada con problemas de rutas o direcciones en la base de datos</p>
  
  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/09f07ac2-bee4-462a-aa05-030ed7808f32)
  </li>
  <li><b>StorageException</b>
  <p>Aqui se representan excepciones relacionadas con el servicio de almacenamiento</p>

  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/72f9315c-d67c-4066-b119-4b72a5df9f50)
  </li>
</ul>
<h2>Carpeta Locale</h2>
<p>Esta carpeta esta encargada de con la clase MyLocale para formatear fechas y cantidades de dinero de acuerdo con la configuración regional y el idioma de España</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/4320eceb-e9f4-4868-aad7-a67ce1ffecda)

<h2>Carpeta Models</h2>
<P>Esta carpeta tiene los modelos del cual se utilizaran en los repositorios y servicios.</P>
<ul>
  <li><b>Funko</b>
  <p>Aqui se muestran las propiedades de Funko junto en como se mostraran por pantalla</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/8bd773fc-630e-4bfb-b4d6-5bb88cd2410c)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/cd145cbe-7c70-4d02-ab76-f081c88e4495)

  </li>
  
  <li><b>IdGenerator</b>
  <p>Aqui se muestra el generador de Ids de forma reactiva con Mono.</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/173f8b15-e41e-472a-8e54-b4fb0d4a0bc2)

  </li>
  
  <li><b>Model</b>
  <p>Aqui se muestra los modelos de cada Funko como estan separados</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/961b9799-2d74-4df1-a491-ea8aebd5a50d)

  </li>
  <li><b>Notificacion</b>
  <p>Aqui se muestra el funcionamiento de nuestras notificaciones.</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/4fe16ce0-0089-48a4-883a-9dddef013f92)

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
<p>Aqui se encuentra la interfaz FunkosRepositories y la clase FunkosRepositoryImpl donde se realizaran las operaciones de Crud y findByNombre,update y save.</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/c36db428-b6ab-4235-93c6-2dbbe9128a62)
<br>

  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/fbd2b7ce-5593-46da-b52c-f910e5f0909f)
  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/9e6a6006-4332-443d-b258-2a283d3e38f4)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/a95aed91-210f-47fc-a1c5-29aceb51a6a6)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/45e68546-3e23-4002-9c19-8e95038273b8)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/843708bd-3a6b-4e6d-95e6-bad5736e6508)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/e18cd707-0c5f-47af-996d-b81ba772f2e9)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/d35f0d6d-c460-4c85-a83c-a062136b6a92)

</li>
</ul>

<h2>Carpeta Services</h2>
<p>Esta carpeta se puede encontrar las subcarpetas cache, database y funkos, donde se creara el cache de funkos, el manejador de datos de la base de datos de Funkos, los Servicios de Funkos y las Notificaciones de Funkos.</p>
<ul>
  <li><b>Carpeta Cache</b>
  <p>Aqui se encuentra la interfaz del cache de forma reactiva</p>

  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/18db7ab9-c382-4620-806e-df71ebde828b)
  </li>

  <li><b>Carpeta Database</b>
  <p>Aqui se encuentra la clase DataBaseManager para poder utilizar la base de datos de Funkos.</p>

 ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/6663685d-14b3-4a33-8d1e-5998eaaa428a)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/420d7176-28ac-4386-8bf0-8f43c9d9cafc)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/369f9660-d173-4ce7-bd3b-bcf0c6fe6a71)

  </li>

  <li><b>Carpeta funkos</b>
  <p>Aqui se crean los servicios de cache, notificacion y Servicios de Funkos</p>
    <b>Funko cache interfaz:</b>

  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/4aa35a9b-5a01-4a3b-bdd7-cf3829fc6c53)
  <br>  <b>FunkoCacheImpl</b> <p>Esto se encarga de realizar los servicios de la interfaz de la cache de Funko.</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/64b5f7a0-127a-45dc-af45-abde3bc2d45c)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/ad53466d-e720-4e64-a11a-ad254f551118)


<br> <b>FunkoNotification</b> <p>Aqui se crean las interfaz para utlizarse en las notificaciones de Funko</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/9cfb9fe1-00c8-4cee-a7e4-c273f12c0542)


<br> <b>FunkoNotificactionImpl</b> <p>Esta clase proporciona una forma reactiva de gestionar una colección de figuras Funko, permitiendo a los clientes recibir notificaciones cuando se agregan o eliminan figuras, así como notificaciones generales.</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/3eeb5fb3-0727-489f-b5f4-d377922080dd)


<br> <b>FunkoService interfaz</b> <p>Esta interfaz define un conjunto de métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en Funko de forma reactiva con Mono y Flux.</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/5eb210ad-d569-42d5-86bd-560def9fa51a)


<br> <b>FunkoServiceImpl</b> <p>Es una implementación de la interfaz FunkosRepository que actúa como una capa de servicio para operaciones relacionadas con figuras Funko y notificaciones.</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/00387e34-bf10-4526-aca5-a04e14d80649)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/95facea1-851b-4807-a607-404cf5fa8221)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/801748da-4e6c-4606-baed-e2430502e2cb)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/64820516-76b6-41ba-8def-9ecbf99def94)

  </li>
  <br> <b>FunkoStorage</b> <p>Esta interfaz proporciona una forma para  gestionar una colección de figuras Funko.</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/de383bc6-06fd-441e-8d4a-1e929b955013)


<br> <b>FunkoService interfaz</b> <p>Esta clase se encarga tanto de las operaciones para gestionar una coleccion de funko como el de importar el csv y exportar json.</p>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/73e79f25-f02c-4b5c-a8b8-333139c2d8eb)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/ca004414-fc9a-49fd-adef-929dec904b7d)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/12ecc70e-c9fc-4bad-b356-ac4c62820ed0)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/9125bfeb-2e95-499b-b1b7-09eb164c03d5)

<br>
</ul>
<h2>Carpeta Storage</h2>

<p>Esta carpeta esta encargada de crear un almacenamiento para cuando se exporte el JSON y importe un csv.</p>
<b>Interfaz Storage</b>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/0e87faf6-12d6-49e8-9d62-8ce3717b53a3)

<h2>Carpeta Utils</h2>

<p>Esta carpeta esta encargada de crear bien los datos se cargen de la forma que queremos y el Uuid de forma para que se muestre bien a la hora de exportarlos a json</p>
<b>LocaldateAdapter</b>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/199ffe7a-95be-49b1-9e6c-f22d1d8714a2)
<br>
<b>LocaldataTimeAdapter</b> 
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/393359f4-ad50-4b64-b7bd-2733dc6d1943)
<br> <br>
<b>UuidAdapter</b><br>
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/08140ae7-98bb-4815-8707-779f5e17ee2c)
<br>
<p>Como se puede ver los tres se encargan de poder leer y escribir los datos para ser exportados adecuadamente a json</p>

<h2>Clase Main</h2>
<p>Aqui esta el main del cual se encargara de realizar las notificaciones y hacer consultas a Funkos (API)</p>

<P>NOTFICACIONES:</P>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/20fdd800-a062-49c8-a888-042090c20d84)

<p>API</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/1cbba433-01c2-4dc1-948d-a54db6e1918b)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/0fabd717-3410-41ce-ba30-ef3d388dbe10)

<h2>Carpeta Resources</h2>
<p>Aqui dentro se encuentra las propiedades de la base de datos de Funkos con sus propiedades configuradas para H2 con R2DBC</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/d65c5fc6-da80-45fb-9eb7-c7392695c9f1)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/dd092503-f4ed-4ac3-be42-8e1c12a52987)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/667943fe-f05d-4fbc-9e35-27d7d68eecd6)



<h2>Carpeta test</h2>
<p>He aqui los test necesarios para los servicios de Funkos.</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/16ccbdda-3511-4f7d-9578-b7c73742601e)

<h2>Carpeta develop / repositories</h2>
<ul>
  <li><b>FunkosRepositoryImplTest</b>
  <p>Aqui se hacen las pruebas para comprobar que el repositorio de funkos creado se pueda realizar las operaciones dentro de ella</p>
   
  ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/ab080e71-9d00-4d25-b9d8-d1bd5c91fbe4)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/3cd468c0-cb14-4192-84c7-883d24167b4c)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/374c1fe8-9282-4147-85ae-87118ab6c9fa)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/5779798b-88a2-4a5c-8ab4-17462fce6567)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/715dc59e-4c4a-4587-b577-d576eb07ff9a)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/df0b207c-682c-432a-8113-c5164e847d93)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/6c3a82f7-6f65-4cc7-9056-5f77a746b96d)

  </li>
</ul>
<h2>Carpeta develop / services </h2>
<ul>
  <li><b>FunkoCacheTest</b>
  <p>Aqui se crean los test para la implementacion de cache y sus operaciones para comprobar que se hayan creado correctamente.</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/a56a8e9b-12a1-4859-89f2-55448bf2b3e1)
 ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/3590cdd8-35c7-4ad9-8eab-0df0b99aae82)
 ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/c0ce0729-550f-4d71-953d-8e8f8f99339b)
 ![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/77d585ba-9c52-4f5a-85bf-a10da55ef206)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/8d031b59-90e4-4241-9706-564c6366c72f)

  
  </li>
   <li><b>FunkoServiceImplTest</b>
   <p>Aqui se encarga los test y pruebas de comprobar que las implementacions de los servicios funcionen correctamente.</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/db891d5a-11ac-4831-b99e-1414543895b0)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/b04a30f5-0fd6-44b4-958b-65be0ad2f7da)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/05da5cba-fe1a-427d-a598-99b2413b6765)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/a1504a93-caa9-4ded-90d0-0832acf8192b)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/eb2ad378-4076-4f50-9fcf-8334c13202ce)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/81e90da7-7be1-4f42-9db1-100e17691571)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/f94ce1ec-5986-4512-9072-8f1acf2533db)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/35a92f88-e73b-49ee-bc68-9ffc19c56993)

   </li>
   <li><b>FunkoStorageTest</b>
   <p>En este test se comprueba si se importan los csv y exportat los json de manera correcta</p>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/f13083d8-6421-49c1-8e77-2a2f5df84dd5)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/36d5ec7a-d1ef-4f7a-b27f-849ad27ca7fe)
![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/30e701f5-40bd-4294-977a-dcf98177e2c4)
   </li>
</ul>
<h1>DEPENDENCIAS UTILIZADAS</h1>

![image](https://github.com/LauraGarridoArredondo/FunkosReactivos_/assets/132077920/6187a3dd-a820-4c70-9911-880d8816e200)



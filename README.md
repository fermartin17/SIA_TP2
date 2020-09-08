# SIA_TP2

Para correr el tp desde consola, hay que ubicarse a la altuna donde se encuentra el pom.xml (adentro de la carpeta TP) y correr el siguiente comando:

mvn exec:java -Dexec.mainClass="TP.App"

MUY IMPORTANTE!!

Como explicaremos en la exposicion, armamos un docker con redis para que se mantengan almacenados los equipamientos.
Para esto deben seguir los siguientes pasos:

1_ Bajarse docker

2_ Una vez bajado docker correr el siguiente comando: docker pull femartin/sia_tp2:sia_tp
   (usando "docker images" podran ver que tiene el repositorio femartin/sia_tp2 con el tag sia_tp)
   
3_ levantar el docker con el siguiente comando: docker run --publish 6379:6379 --detach femartin/sia_tp2:sia_tp

  (en caso de tener otro proceso en ese puerto por favor utilizar el siguiente comando para matarlo: sudo kill $(sudo lsof -t -i:6379)

4_ Una vez corrido el docker ya tenemos la imagen escuchando en ese puerto, lo proximo a continuacion es correr el programa la primera vez con el siguiente segmento de configuracion (es decir, dejar el archivo de configuracion completo pero setear parametros en esta unica seccion):

   "dataConf":{
      "setData":true,
      "helmetPath":"/home/fer/Documents/SIA/TP2/fulldata/cascos.tsv",
      "frontPath":"/home/fer/Documents/SIA/TP2/fulldata/pecheras.tsv",
      "weaponsPath":"/home/fer/Documents/SIA/TP2/fulldata/armas.tsv",
      "glovesPath":"/home/fer/Documents/SIA/TP2/fulldata/guantes.tsv",
      "bootsPath":"/home/fer/Documents/SIA/TP2/fulldata/botas.tsv"
   } 

donde alli se parametrizan los path de los archivos tsv.

5_ Una vez completado el paso 4, setear en "setData" en false y ya no volver a utilizarlo, asi ya vamos a tener toda la data necesaria en el docker.

6_ Correr y utilizar el resto de las parametrizaciones para las pruebas que deseen.

# WebShop
El microservicio de gestión del catálago de productos le permitirá a los consumidores crear, actualizar, mostrar y eliminar información sobre los productos que se venden en la tienda en línea WebShop.

## Indicar la ruta del volumen
##### Unix
En Unix y en sistemas similares como Linux y macOS, se puede usar la variable de entorno $PWD para indicar la ruta actual
##### Ejemplo Unix
docker run -p 9090:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -d --rm --volume $PWD:/opt/keycloak/data/ quay.io/keycloak/keycloak:21.0.2 start-dev


##### Windows
En Windows, la variable equivalente sería %CD%
##### Ejemplo Windows
docker run -p 9090:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -d --rm --volume %CD%:/opt/keycloak/data/ quay.io/keycloak/keycloak:21.0.2 start-dev
Configuration et Lancement de l'API Spring Boot
Ce document fournit des instructions détaillées pour configurer et lancer l'API Spring Boot.

Prérequis
Avant de commencer, assurez-vous d'avoir les éléments suivants installés :

Java Development Kit (JDK) : Version 17 ou supérieure.

PostgreSQL : Version 14 ou supérieure.

Maven : Version 3.6.3 ou supérieure.

IntelliJ IDEA/Eclipse/VSCode : (Optionnel) Un IDE pour faciliter le développement.

Configuration de la Base de Données
Créer une base de données PostgreSQL :

Ouvrez votre client PostgreSQL (par exemple, pgAdmin).

Créez une nouvelle base de données nommée inventory_db.

Configurer les propriétés de la base de données :

Modifiez le fichier application.properties pour qu'il corresponde à votre configuration PostgreSQL.

```properties
spring.application.name=inventory
server.port=9000

spring.datasource.url=jdbc:postgresql://localhost:5432/inventory_db
spring.datasource.username=postgres
spring.datasource.password=votre_mot_de_passe
spring.datasource.driver-class-name=org.postgresql.Driver

spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

api.prefix=/api/v1
```

Assurez-vous de remplacer votre_mot_de_passe par votre mot de passe PostgreSQL.

spring.jpa.hibernate.ddl-auto=create : Ceci créera automatiquement les tables de la base de données au démarrage de l'application. Attention : En production, il est recommandé d'utiliser des outils de migration de base de données comme Flyway ou Liquibase.

Construction et Lancement de l'API
Vous avez deux options pour construire et lancer l'API :

Option 1 : Utilisation de Maven
Ouvrez un terminal : Accédez au répertoire racine du projet où se trouve le fichier pom.xml.

Nettoyez et construisez le projet :

```
mvn clean install
```

Exécutez l'application :

```
mvn spring-boot:run
```

Option 2 : Utilisation d'un IDE (IntelliJ IDEA, Eclipse, VSCode)
Importez le projet : Importez le projet dans votre IDE en tant que projet Maven.

Exécutez l'application :

Localisez la classe principale de l'application (celle annotée avec @SpringBootApplication, probablement une classe nommée InventoryApplication).

Faites un clic droit sur la classe et sélectionnez "Run" ou "Debug".

Vérification du Lancement
Une fois l'application lancée, vous devriez voir des messages de log indiquant que Spring Boot a démarré avec succès. Vous pouvez vérifier que l'application est en cours d'exécution en ouvrant votre navigateur et en accédant à l'URL suivante : http://localhost:9000/api/v1/products (ou toute autre endpoint que vous avez configuré). Cela devrait retourner une réponse JSON contenant une liste de produits (ou un message vide si vous n'avez pas encore ajouté de produits).

Points importants
Port de l'application : L'application s'exécute par défaut sur le port 9000. Vous pouvez modifier cela dans le fichier application.properties en changeant la valeur de server.port.

Préfixe de l'API : Toutes les endpoints de l'API sont préfixées par /api/v1. Ceci est défini par la propriété api.prefix dans application.properties.

Gestion des exceptions : L'application utilise un GlobalExceptionHandler pour gérer les exceptions de manière centralisée. Les exceptions ResourceNotFoundException et MethodArgumentNotValidException sont gérées, et une réponse d'erreur appropriée est renvoyée au client.

Validation des données : La validation des données est assurée par l'annotation @Valid et les contraintes de validation de Bean (par exemple, @NotBlank, @NotNull) dans les classes DTO.

Swagger : La configuration actuelle ne comprend pas Swagger, mais il est fortement recommandé de l'ajouter pour faciliter la documentation et les tests de l'API.

Lombok : Le projet utilise Lombok pour réduire le code boilerplate. Les annotations @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor, et @RequiredArgsConstructor sont utilisées.

Logging : L'application utilise Slf4j et Logback pour la journalisation. Les messages de log importants, tels que les alertes de stock bas, sont enregistrés.

Annotations Importantes
Voici une liste des annotations les plus importantes utilisées dans le code :

@RestController : Indique que la classe est un contrôleur et que chaque méthode renvoie une réponse.

@RequestMapping : Définit le chemin de base pour toutes les méthodes du contrôleur.

@GetMapping, @PostMapping, @PutMapping, @DeleteMapping : Définissent le type de requête HTTP pour chaque méthode.

@RequestBody : Indique que le corps de la requête doit être converti en un objet.

@RequestParam : Extrait les paramètres de la requête de l'URL.

@Valid : Valide le corps de la requête (DTO) selon les contraintes définies.

@Service : Indique que la classe est un composant de service.

@Repository : Indique que la classe est un composant de repository (pour l'accès aux données).

@Entity : Définit une classe comme une entité JPA qui représente une table dans la base de données.

@Id : Définit le champ comme la clé primaire de l'entité.

@GeneratedValue : Spécifie la stratégie de génération de la valeur de la clé primaire.

@ExceptionHandler : Définit une méthode pour gérer des exceptions spécifiques.

@RestControllerAdvice : Intercepte les exceptions levées par les contrôleurs.

@Slf4j : Génère automatiquement un champ log de type org.slf4j.Logger.

@RequiredArgsConstructor : Génère un constructeur avec des arguments pour tous les champs final ou @NonNull.

@Tag : Utilisé pour la documentation Swagger.

@Operation : Utilisé pour la documentation Swagger.

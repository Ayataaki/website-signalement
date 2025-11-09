# Plateforme de signalement et suivi des problèmes urbains

## Contexte
Le service municipal souhaite développer un système permettant la gestion des signalements des problèmes urbains. En effet, chaque région possède plusieurs services municipaux et chaque citoyen appartient à un service municipal précis.

## Objectif
Permettre aux citoyens de signaler et suivre des problèmes urbains (déchets, éclairage, routes). Ainsi qu'aux employés et administrateurs de gérer ces derniers et affecter les tâches aux techniciens.

---

## 🐳 Docker & Base de données

### Configuration Docker Compose

Le projet utilise **Docker** pour containeriser la base de données MySQL, garantissant ainsi un environnement de développement cohérent et reproductible.

**Fichier `docker-compose.yml` :**
```yaml
services:
  mysql:
    image: mysql:8.0
    container_name: signalement_mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: website_signalement_draft
      MYSQL_USER: admin
      MYSQL_PASSWORD: admin123
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql
    restart: unless-stopped
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      timeout: 20s
      retries: 10

volumes:
  mysql-data:
```

### Démarrer la base de données

```bash
# Démarrer le conteneur MySQL
docker-compose up -d

# Vérifier que MySQL est en cours d'exécution
docker ps

# Voir les logs
docker logs signalement_mysql

# Se connecter à MySQL
docker exec -it signalement_mysql mysql -u root -proot

# Arrêter le conteneur
docker-compose down

# Arrêter et supprimer les données (⚠️ supprime toutes les données)
docker-compose down -v
```

### Configuration de connexion

La connexion à la base de données est gérée via la classe `SingletonConnection` qui utilise le pattern Singleton pour garantir une seule instance de connexion.

**Paramètres de connexion :**
- **URL** : `jdbc:mysql://localhost:3306/website_signalement_draft?serverTimezone=UTC`
- **Utilisateur** : `root` (ou `admin`)
- **Mot de passe** : `root` (ou `admin123`)

---

## 🦅 Migrations Flyway

### Qu'est-ce que Flyway ?

**Flyway** est un outil de gestion des migrations de base de données qui permet de :
- Versionner le schéma de la base de données
- Appliquer automatiquement les changements de structure
- Suivre l'historique des migrations
- Garantir la cohérence entre environnements

### Structure des migrations

Les scripts de migration sont situés dans : `src/main/resources/db/migration/`

```
db/migration/
├── V1__Create_employe_table.sql
├── V2__Create_region_table.sql
├── V3__Create_technicien_table.sql
├── V4__Create_municipal_table.sql
├── V5__add_foreign_key_employe_municipal.sql
├── V6__Create_citoyen_table.sql
├── V7__Create_signalement_table.sql
└── V8__add_indexes.sql
```

**Convention de nommage :**
- `V{version}__{description}.sql`
- Exemple : `V1__Create_employe_table.sql`
- Les versions doivent être **séquentielles**
- Les descriptions doivent être **descriptives**

### Comment Flyway fonctionne

1. **Au démarrage de l'application**, Flyway :
   - Crée une table `flyway_schema_history` pour tracer les migrations
   - Compare les scripts de migration avec l'historique
   - Applique automatiquement les nouvelles migrations dans l'ordre

2. **Table de suivi** : `flyway_schema_history`
```sql
SELECT * FROM flyway_schema_history;
-- Affiche : version, description, script, success, execution_time
```

### Configuration Flyway

**Dans `SingletonConnection.java` :**
```java
private static void migrateDatabase() {
    Flyway flyway = Flyway.configure()
        .dataSource(URL, USER, PASSWORD)
        .baselineOnMigrate(true)
        .outOfOrder(false)
        .locations("classpath:db/migration")
        .load();
    
    flyway.repair();  // Nettoie les migrations échouées
    flyway.migrate(); // Applique les migrations
}
```

### Commandes utiles

```bash
# Voir l'état des migrations dans MySQL
docker exec -it signalement_mysql mysql -u root -proot -e "
USE website_signalement_draft;
SELECT installed_rank, version, description, success 
FROM flyway_schema_history 
ORDER BY installed_rank;"

# Voir les tables créées
docker exec -it signalement_mysql mysql -u root -proot -e "
USE website_signalement_draft;
SHOW TABLES;"
```

### Résolution de problèmes

**Migration échouée ?**
1. Vérifier les logs de l'application
2. Consulter `flyway_schema_history` pour voir quelle migration a échoué
3. Corriger le script SQL
4. Nettoyer et redémarrer :
```bash
docker-compose down -v
docker-compose up -d
# Attendre 30 secondes
# Relancer l'application
```

---

## 🏗️ Architecture & Design Patterns

### Pattern DAO (Data Access Object)

Le projet utilise le **pattern DAO** pour séparer la logique d'accès aux données de la logique métier.

**Structure :**
```
dao/
├── interfaces/
│   ├── IEmployeDao.java
│   ├── ICitoyenDao.java
│   ├── ISignalementDao.java
│   └── ITechnicienDao.java
└── implementations/
    ├── EmployeDaoImpl.java
    ├── CitoyenDaoImpl.java
    ├── SignalementDaoImpl.java
    └── TechnicienDaoImpl.java
```

**Avantages :**
- ✅ **Séparation des responsabilités** : La couche DAO gère uniquement l'accès aux données
- ✅ **Testabilité** : Facile de créer des mock pour les tests
- ✅ **Maintenabilité** : Changement de base de données sans toucher au code métier
- ✅ **Réutilisabilité** : Les interfaces peuvent avoir plusieurs implémentations

**Exemple d'interface :**
```java
public interface IEmployeDao {
    void create(Employe employe);
    Employe findById(Long id);
    List<Employe> findAll();
    void update(Employe employe);
    void delete(Long id);
}
```

**Exemple d'implémentation :**
```java
public class EmployeDaoImpl implements IEmployeDao {
    private Connection connection = SingletonConnection.getConnection();
    
    @Override
    public void create(Employe employe) {
        String sql = "INSERT INTO employe (nom, prenom, email) VALUES (?, ?, ?)";
        // Implémentation CRUD...
    }
    
    // Autres méthodes CRUD...
}
```

### Pattern Singleton

La connexion à la base de données utilise le **pattern Singleton** via `SingletonConnection.java` :
- ✅ **Une seule instance** de connexion dans toute l'application
- ✅ **Initialisation au démarrage** avec migrations Flyway
- ✅ **Gestion centralisée** des erreurs de connexion

---

## 📦 Dépendances & Librairies

### Librairies requises

Les bibliothèques suivantes doivent être présentes dans `webapp/WEB-INF/lib/` :

**Base de données & Migrations :**
- `flyway-core-9.8.3.jar`
- `flyway-mysql-9.8.3.jar`
- `mysql-connector-j-8.0.33.jar`

**JSTL (JavaServer Pages Standard Tag Library) :**
- `jakarta.servlet.jsp.jstl-2.0.0.jar`
- `jakarta.servlet.jsp.jstl-api-2.0.0.jar`
- `taglibs-standard-impl-1.2.5-migrated-0.0.1.jar`
- `taglibs-standard-spec-1.2.5-migrated-0.0.1.jar`

> **Note :** Ces librairies ne sont **pas incluses dans le dépôt Git** (ignorées via `.gitignore`) afin d'éviter d'alourdir le projet.

---

## 🎨 Front-end / Styles

Ce projet utilise **Bootstrap 5** pour le style et la mise en page responsive.

**Fichiers Bootstrap :**
- CSS principal : `webapp/css/bootstrap.min.css`
- JS (composants interactifs) : `webapp/js/bootstrap.bundle.min.js`

**Inclusion dans les pages JSP :**
```jsp
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script src="js/bootstrap.bundle.min.js"></script>
```

---

## ⚙️ Environnement requis

Pour exécuter ce projet correctement, vous devez utiliser :

- **Java JDK 23**
- **Apache Tomcat 10.1**
- **Eclipse IDE for Enterprise Java and Web Developers**
- **Docker Desktop** (pour la base de données MySQL)
- **MySQL Server 8.0** (via Docker)
- Les librairies externes dans `webapp/WEB-INF/lib/` (voir section Dépendances)

---

## 🚀 Installation et déploiement

### 1. Prérequis

```bash
# Vérifier Java
java -version  # Doit afficher Java 23

# Vérifier Docker
docker --version
docker-compose --version
```

### 2. Cloner le projet

```bash
git clone <url-du-repo>
cd plateforme-signalement
```

### 3. Ajouter les librairies

Télécharger et placer les fichiers `.jar` dans `webapp/WEB-INF/lib/` (voir section Dépendances)

### 4. Démarrer la base de données

```bash
# Démarrer MySQL avec Docker
docker-compose up -d

# Vérifier que MySQL est prêt (attendre ~30 secondes)
docker logs -f signalement_mysql
# Quand vous voyez "ready for connections", c'est bon !
```

### 5. Importer dans Eclipse

1. `File → Import → Existing Projects into Workspace`
2. Sélectionner le dossier du projet
3. `Finish`

### 6. Configurer Tomcat dans Eclipse

1. `Window → Preferences → Server → Runtime Environments`
2. `Add → Apache Tomcat 10.1`
3. Spécifier le chemin d'installation de Tomcat

### 7. Déployer et exécuter

1. Clic droit sur le projet → `Run As → Run on Server`
2. Sélectionner **Tomcat 10.1**
3. Eclipse compile et déploie automatiquement
4. L'application se lance sur : `http://localhost:8080/plateforme-signalement`

**Au premier lancement :**
- Flyway crée automatiquement toutes les tables
- Les migrations s'appliquent dans l'ordre
- Vérifier les logs dans la console Eclipse

---

## 🔧 Configuration avancée

### Variables d'environnement (optionnel)

Pour une configuration plus flexible, vous pouvez utiliser des variables d'environnement au lieu de valeurs codées en dur :

```java
// Dans SingletonConnection.java
private static final String URL = System.getenv().getOrDefault(
    "DB_URL", 
    "jdbc:mysql://localhost:3306/website_signalement_draft?serverTimezone=UTC"
);
```

### Mode développement vs Production

**Développement :**
```java
Flyway flyway = Flyway.configure()
    .dataSource(URL, USER, PASSWORD)
    .cleanDisabled(false)  // Permet clean en dev
    .locations("classpath:db/migration")
    .load();
```

**Production :**
```java
Flyway flyway = Flyway.configure()
    .dataSource(URL, USER, PASSWORD)
    .cleanDisabled(true)   // Désactive clean en prod
    .locations("classpath:db/migration")
    .load();
```

---

## 🐛 Résolution de problèmes

### Erreur : "Table doesn't exist"

**Cause :** Migration Flyway échouée ou incomplète

**Solution :**
```bash
# Reset complet de la base
docker-compose down -v
docker-compose up -d
# Attendre 30 secondes
# Relancer l'application
```

### Erreur : "Access denied for user"

**Cause :** Mauvais identifiants de connexion

**Solution :**
1. Vérifier `docker-compose.yml` : utilisateur et mot de passe
2. Vérifier `SingletonConnection.java` : même utilisateur/mot de passe
3. Redémarrer Docker : `docker-compose restart`

### Erreur : "Unable to execute clean"

**Cause :** Flyway clean est désactivé

**Solution :** Enlever `flyway.clean()` du code ou activer avec `.cleanDisabled(false)`

### MySQL ne démarre pas

```bash
# Vérifier les logs
docker logs signalement_mysql

# Vérifier que le port 3306 est libre
netstat -an | grep 3306

# Redémarrer Docker
docker-compose restart
```

### Tomcat 10 : Erreur de compilation

**Cause :** Imports `javax.servlet.*` au lieu de `jakarta.servlet.*`

**Solution :** Remplacer tous les imports :
```java
// ❌ Ancien (Java EE)
import javax.servlet.*;

// ✅ Nouveau (Jakarta EE)
import jakarta.servlet.*;
```

---

## 📚 Structure du projet

```
plateforme-signalement/
├── src/
│   └── main/
│       ├── java/
│       │   ├── dao/
│       │   │   ├── interfaces/
│       │   │   │   ├── IEmployeDao.java
│       │   │   │   ├── ICitoyenDao.java
│       │   │   │   └── ...
│       │   │   └── implementations/
│       │   │       ├── EmployeDaoImpl.java
│       │   │       ├── CitoyenDaoImpl.java
│       │   │       └── ...
│       │   ├── servlets/
│       │   ├── models/
│       │   └── utils/
│       │       └── SingletonConnection.java
│       └── resources/
│           └── db/
│               └── migration/
│                   ├── V1__Create_employe_table.sql
│                   ├── V2__Create_region_table.sql
│                   └── ...
├── webapp/
│   ├── WEB-INF/
│   │   ├── lib/               # Librairies .jar
│   │   └── web.xml
│   ├── css/
│   │   └── bootstrap.min.css
│   ├── js/
│   │   └── bootstrap.bundle.min.js
│   └── jsp/
├── docker-compose.yml          # Configuration Docker
└── README.md
```

---

## 📝 Changelog

### Version actuelle

**Nouvelles fonctionnalités :**
- ✅ Containerisation de MySQL avec Docker
- ✅ Migrations automatiques avec Flyway
- ✅ Pattern DAO avec interfaces et implémentations
- ✅ CRUD complet pour toutes les entités
- ✅ Gestion centralisée de la connexion (Singleton)

**Améliorations techniques :**
- Migration de `javax.*` vers `jakarta.*`
- Utilisation de Docker pour l'environnement de développement
- Versioning du schéma de base de données
- Architecture en couches (DAO, Service, Servlet)

---

## 👥 Auteurs

**Aya Taki** & **Hajar Braidi**

---

## 📄 Licence

Ce projet est développé dans un cadre académique.

---

## 🔗 Ressources utiles

- [Documentation Flyway](https://flywaydb.org/documentation/)
- [Docker Compose](https://docs.docker.com/compose/)
- [MySQL 8.0](https://dev.mysql.com/doc/refman/8.0/en/)
- [Apache Tomcat 10.1](https://tomcat.apache.org/tomcat-10.1-doc/)
- [Bootstrap 5](https://getbootstrap.com/docs/5.0/)
- [Jakarta EE](https://jakarta.ee/)

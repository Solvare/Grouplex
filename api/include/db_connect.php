<?php

/**
 * Handling database connection
 */
class DbConnect {

    private $conn;

    function __construct() {        
    }

    /**
     * Establishing database connection
     * @return database connection handler
     */
    function connect() {
        include_once dirname(__FILE__) . '/config.php';

        // Connecting to mysql database
        try
        {
            $this->conn = new PDO($dsn, $user, $pass);

        }
        catch (PDOException $e)
        {
            print "Error!: " . $e->getMessage() . "<br/>";
            die();
        }

        // returing connection resource
        return $this->conn;
    }

}

?>

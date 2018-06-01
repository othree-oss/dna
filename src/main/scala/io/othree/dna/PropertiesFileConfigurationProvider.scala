package io.othree.dna

import java.io.InputStream
import java.util.Properties

abstract class PropertiesFileConfigurationProvider(inputStream:InputStream) extends ConfigurationProvider {
  val configProperties = new Properties()
  configProperties.load(inputStream)

  override def getProperty(propertyName: String): String = {
    configProperties.getProperty(propertyName, "")
  }

  override def getOptionalProperty(propertyName: String): Option[String] = {
    val propertyValue = configProperties.getProperty(propertyName)
    Option(propertyValue)
  }

  override def getPropertyAsArray(propertyName: String): Array[String] = {
    val value = getProperty(propertyName)
    if (value == "") {
      Array()
    } else {
      value.split(",")
    }
  }
}

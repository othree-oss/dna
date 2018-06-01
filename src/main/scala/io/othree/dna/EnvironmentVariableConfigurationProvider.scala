package io.othree.dna

abstract class EnvironmentVariableConfigurationProvider extends ConfigurationProvider{
  override def getProperty(propertyName: String): String = {
    scala.util.Properties.envOrElse(propertyName,"")
  }

  override def getOptionalProperty(propertyName: String): Option[String] = {
    scala.util.Properties.envOrNone(propertyName)
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

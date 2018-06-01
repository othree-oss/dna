package io.othree.dna

import com.typesafe.config.Config

abstract class TSConfigurationProvider(config: Config) extends ConfigurationProvider {

  override def getProperty(propertyName:String) : String = {
    if (config.hasPath(propertyName)) {
      config.getString(propertyName)
    } else {
      ""
    }
  }

  override def getOptionalProperty(propertyName:String) : Option[String] = {
    if (config.hasPath(propertyName)) {
      Some(config.getString(propertyName))
    } else {
      None
    }
  }

  override def getPropertyAsArray(propertyName: String): Array[String] = {
    if (config.hasPath(propertyName)) {
      val values = config.getStringList(propertyName)
      values.toArray(new Array[String](values.size()))
    } else {
      Array()
    }
  }
}

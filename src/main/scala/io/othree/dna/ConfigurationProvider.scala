package io.othree.dna

trait ConfigurationProvider {

  def getProperty(propertyName: String): String

  def getOptionalProperty(propertyName: String): Option[String]

  def getPropertyAsArray(propertyName: String): Array[String]

}

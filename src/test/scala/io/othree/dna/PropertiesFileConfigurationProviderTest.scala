package io.othree.dna

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import io.othree.aok.BaseTest

@RunWith(classOf[JUnitRunner])
class PropertiesFileConfigurationProviderTest extends BaseTest {

  var configurationProvider : PropertiesFileConfigurationProvider = _

  before {
    val propertiesFile = this.getClass.getResourceAsStream("/configuration.properties")
    class TestConfiguration extends PropertiesFileConfigurationProvider(propertiesFile)

    configurationProvider = new TestConfiguration
  }

  "EnvironmentVariableConfigurationProvider" when {
    "calling getProperty" must {
      "return the property value" in {
        val value = configurationProvider.getProperty("some.value")

        value shouldBe "value"
      }

      "return an empty string if the property does not exist" in {
        val value = configurationProvider.getProperty("non.existent")

        value shouldBe ""
      }
    }


    "calling getOptionalProperty" must {
      "return the property value" in {
        val value = configurationProvider.getOptionalProperty("some.value")

        value shouldBe Some("value")
      }

      "return an Empty Option if the property does not exist" in {
        val value = configurationProvider.getOptionalProperty("non.existent")

        value shouldBe None
      }
    }

    "calling getPropertyAsArray" must {
      "return the configured values" in {
        val values = configurationProvider.getPropertyAsArray("values")

        values should have size 3
        values should contain ("one")
        values should contain ("two")
        values should contain ("othree")
      }

      "return an empty array if the property does not exist" in {
        val values = configurationProvider.getPropertyAsArray("NON_EXISTENT")

        values shouldBe empty
      }
    }
  }

}

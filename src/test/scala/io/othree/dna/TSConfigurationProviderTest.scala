package io.othree.dna

import com.typesafe.config.ConfigFactory
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import io.othree.aok.BaseTest

@RunWith(classOf[JUnitRunner])
class TSConfigurationProviderTest extends BaseTest {

  var configurationProvider : TSConfigurationProvider = _

  before {
    class TestConfiguration extends TSConfigurationProvider(ConfigFactory.load())

    configurationProvider = new TestConfiguration
  }

  "TSConfigurationProvider" when {
    "calling getProperty" must {
      "return the property value" in {
        val value = configurationProvider.getProperty("configurationValues.host")

        value shouldBe "127.0.0.1"
      }

      "return an empty string if the property does not exist" in {
        val value = configurationProvider.getProperty("NON_EXISTENT")

        value shouldBe ""
      }
    }


    "calling getOptionalProperty" must {
      "return the property value" in {
        val value = configurationProvider.getOptionalProperty("configurationValues.host")

        value shouldBe Some("127.0.0.1")
      }

      "return an Empty Option if the property does not exist" in {
        val value = configurationProvider.getOptionalProperty("NON_EXISTENT")

        value shouldBe None
      }
    }

    "calling getPropertyAsArray" must {
      "return the configured values" in {
        val values = configurationProvider.getPropertyAsArray("configurationValues.values")

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

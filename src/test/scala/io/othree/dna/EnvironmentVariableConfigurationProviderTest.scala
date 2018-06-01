package io.othree.dna

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import io.othree.aok.BaseTest
import io.othree.aok.Environment.setEnv

@RunWith(classOf[JUnitRunner])
class EnvironmentVariableConfigurationProviderTest extends BaseTest {

  var configurationProvider : EnvironmentVariableConfigurationProvider = _

  before {
    class TestConfiguration extends EnvironmentVariableConfigurationProvider

    configurationProvider = new TestConfiguration

    setEnv("SOME_CONFIG", "value")
    setEnv("VALUES", "one,two,othree")
  }

  "EnvironmentVariableConfigurationProvider" when {
    "calling getProperty" must {
      "return the property value" in {
        val value = configurationProvider.getProperty("SOME_CONFIG")

        value shouldBe "value"
      }

      "return an empty string if the property does not exist" in {
        val value = configurationProvider.getProperty("NON_EXISTENT")

        value shouldBe ""
      }
    }


    "calling getOptionalProperty" must {
      "return the property value" in {
        val value = configurationProvider.getOptionalProperty("SOME_CONFIG")

        value shouldBe Some("value")
      }

      "return an Empty Option if the property does not exist" in {
        val value = configurationProvider.getOptionalProperty("NON_EXISTENT")

        value shouldBe None
      }
    }

    "calling getPropertyAsArray" must {
      "return the configured values" in {
        val values = configurationProvider.getPropertyAsArray("VALUES")

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

/*
 * ProductOrdering
 * Please add description
 *
 * OpenAPI spec version: 0.1.0_inProgress
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.atos.ojo.model.lizard;
import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Describes a given characteristic of an object or entity through a name/value pair.
 */
@Schema(description = "Describes a given characteristic of an object or entity through a name/value pair.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-13T15:28:56.741+02:00[Africa/Cairo]")
public class Characteristic {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("value")
  private String value = null;

  public Characteristic name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of the characteristic
   * @return name
  **/
  @Schema(required = true, description = "Name of the characteristic")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Characteristic value(String value) {
    this.value = value;
    return this;
  }

   /**
   * The value of the characteristic
   * @return value
  **/
  @Schema(required = true, description = "The value of the characteristic")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Characteristic characteristic = (Characteristic) o;
    return Objects.equals(this.name, characteristic.name) &&
        Objects.equals(this.value, characteristic.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, value);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Characteristic {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

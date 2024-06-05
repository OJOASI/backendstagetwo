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
 * An amount in a given unit
 */
@Schema(description = "An amount in a given unit")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-13T15:28:56.741+02:00[Africa/Cairo]")
public class Quantity {
  @JsonProperty("amount")
  private Double amount = 1.0d;

  @JsonProperty("units")
  private String units = null;

  public Quantity amount(Double amount) {
    this.amount = amount;
    return this;
  }

   /**
   * Numeric value in a given unit
   * @return amount
  **/
  @Schema(description = "Numeric value in a given unit")
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Quantity units(String units) {
    this.units = units;
    return this;
  }

   /**
   * Unit
   * @return units
  **/
  @Schema(description = "Unit")
  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Quantity quantity = (Quantity) o;
    return Objects.equals(this.amount, quantity.amount) &&
        Objects.equals(this.units, quantity.units);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, units);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Quantity {\n");
    
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    units: ").append(toIndentedString(units)).append("\n");
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

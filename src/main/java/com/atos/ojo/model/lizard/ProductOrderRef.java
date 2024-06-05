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
 * ProductOrder (ProductOrder) .The product order which the recommendation is related with.
 */
@Schema(description = "ProductOrder (ProductOrder) .The product order which the recommendation is related with.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-13T15:28:56.741+02:00[Africa/Cairo]")
public class ProductOrderRef {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("href")
  private String href = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("@baseType")
  private String _atBaseType = null;

  @JsonProperty("@schemaLocation")
  private String _atSchemaLocation = null;

  @JsonProperty("@type")
  private String _atType = null;

  @JsonProperty("@referredType")
  private String _atReferredType = null;

  public ProductOrderRef id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier of a related entity.
   * @return id
  **/
  @Schema(required = true, description = "Unique identifier of a related entity.")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProductOrderRef href(String href) {
    this.href = href;
    return this;
  }

   /**
   * Reference of the related entity.
   * @return href
  **/
  @Schema(description = "Reference of the related entity.")
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public ProductOrderRef name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of the related entity.
   * @return name
  **/
  @Schema(description = "Name of the related entity.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductOrderRef _atBaseType(String _atBaseType) {
    this._atBaseType = _atBaseType;
    return this;
  }

   /**
   * When sub-classing, this defines the super-class
   * @return _atBaseType
  **/
  @Schema(description = "When sub-classing, this defines the super-class")
  public String getAtBaseType() {
    return _atBaseType;
  }

  public void setAtBaseType(String _atBaseType) {
    this._atBaseType = _atBaseType;
  }

  public ProductOrderRef _atSchemaLocation(String _atSchemaLocation) {
    this._atSchemaLocation = _atSchemaLocation;
    return this;
  }

   /**
   * A URI to a JSON-Schema file that defines additional attributes and relationships
   * @return _atSchemaLocation
  **/
  @Schema(description = "A URI to a JSON-Schema file that defines additional attributes and relationships")
  public String getAtSchemaLocation() {
    return _atSchemaLocation;
  }

  public void setAtSchemaLocation(String _atSchemaLocation) {
    this._atSchemaLocation = _atSchemaLocation;
  }

  public ProductOrderRef _atType(String _atType) {
    this._atType = _atType;
    return this;
  }

   /**
   * When sub-classing, this defines the sub-class entity name
   * @return _atType
  **/
  @Schema(description = "When sub-classing, this defines the sub-class entity name")
  public String getAtType() {
    return _atType;
  }

  public void setAtType(String _atType) {
    this._atType = _atType;
  }

  public ProductOrderRef _atReferredType(String _atReferredType) {
    this._atReferredType = _atReferredType;
    return this;
  }

   /**
   * The actual type of the target instance when needed for disambiguation.
   * @return _atReferredType
  **/
  @Schema(description = "The actual type of the target instance when needed for disambiguation.")
  public String getAtReferredType() {
    return _atReferredType;
  }

  public void setAtReferredType(String _atReferredType) {
    this._atReferredType = _atReferredType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductOrderRef productOrderRef = (ProductOrderRef) o;
    return Objects.equals(this.id, productOrderRef.id) &&
        Objects.equals(this.href, productOrderRef.href) &&
        Objects.equals(this.name, productOrderRef.name) &&
        Objects.equals(this._atBaseType, productOrderRef._atBaseType) &&
        Objects.equals(this._atSchemaLocation, productOrderRef._atSchemaLocation) &&
        Objects.equals(this._atType, productOrderRef._atType) &&
        Objects.equals(this._atReferredType, productOrderRef._atReferredType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, name, _atBaseType, _atSchemaLocation, _atType, _atReferredType);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOrderRef {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    _atBaseType: ").append(toIndentedString(_atBaseType)).append("\n");
    sb.append("    _atSchemaLocation: ").append(toIndentedString(_atSchemaLocation)).append("\n");
    sb.append("    _atType: ").append(toIndentedString(_atType)).append("\n");
    sb.append("    _atReferredType: ").append(toIndentedString(_atReferredType)).append("\n");
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

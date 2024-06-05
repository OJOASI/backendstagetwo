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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * A Product Order is a type of order which  can  be used to place an order between a customer and a service provider or between a service provider and a partner and vice versa, Skipped properties: id,href,completionDate,orderDate,state,expectedCompletionDate,productOrderItem.state
 */
@Schema(description = "A Product Order is a type of order which  can  be used to place an order between a customer and a service provider or between a service provider and a partner and vice versa, Skipped properties: id,href,completionDate,orderDate,state,expectedCompletionDate,productOrderItem.state")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-03-13T15:28:56.741+02:00[Africa/Cairo]")
public class ProductOrderCreate {
  @JsonProperty("category")
  private String category = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("externalId")
  private String externalId = null;

  @JsonProperty("relatedParty")
  private List<RelatedParty> relatedParty = null;

  @JsonProperty("channel")
  private List<RelatedChannel> channel = null;

  @JsonProperty("billingAccount")
  private BillingAccountRef billingAccount = null;

  @JsonProperty("productOrderItem")
  private List<ProductOrderItem> productOrderItem = new ArrayList<>();

  @JsonProperty("relatedPublicKey")
  private List<RelatedPublicKey> relatedPublicKey = null;

  public ProductOrderCreate category(String category) {
    this.category = category;
    return this;
  }

   /**
   * Used to categorize the order from a business perspective that can be useful for the OM system (e.g. \&quot;enterprise\&quot;, \&quot;residential\&quot;, ...)
   * @return category
  **/
  @Schema(description = "Used to categorize the order from a business perspective that can be useful for the OM system (e.g. \"enterprise\", \"residential\", ...)")
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public ProductOrderCreate description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Description of the product order
   * @return description
  **/
  @Schema(description = "Description of the product order")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductOrderCreate externalId(String externalId) {
    this.externalId = externalId;
    return this;
  }

   /**
   * ID given by the consumer and only understandable by him (to facilitate his searches afterwards)
   * @return externalId
  **/
  @Schema(description = "ID given by the consumer and only understandable by him (to facilitate his searches afterwards)")
  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public ProductOrderCreate relatedParty(List<RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public ProductOrderCreate addRelatedPartyItem(RelatedParty relatedPartyItem) {
    if (this.relatedParty == null) {
      this.relatedParty = new ArrayList<>();
    }
    this.relatedParty.add(relatedPartyItem);
    return this;
  }

   /**
   * Get relatedParty
   * @return relatedParty
  **/
  @Schema(description = "")
  public List<RelatedParty> getRelatedParty() {
    return relatedParty;
  }

  public void setRelatedParty(List<RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
  }

  public ProductOrderCreate channel(List<RelatedChannel> channel) {
    this.channel = channel;
    return this;
  }

  public ProductOrderCreate addChannelItem(RelatedChannel channelItem) {
    if (this.channel == null) {
      this.channel = new ArrayList<>();
    }
    this.channel.add(channelItem);
    return this;
  }

   /**
   * Get channel
   * @return channel
  **/
  @Schema(description = "")
  public List<RelatedChannel> getChannel() {
    return channel;
  }

  public void setChannel(List<RelatedChannel> channel) {
    this.channel = channel;
  }

  public ProductOrderCreate billingAccount(BillingAccountRef billingAccount) {
    this.billingAccount = billingAccount;
    return this;
  }

   /**
   * Get billingAccount
   * @return billingAccount
  **/
  @Schema(description = "")
  public BillingAccountRef getBillingAccount() {
    return billingAccount;
  }

  public void setBillingAccount(BillingAccountRef billingAccount) {
    this.billingAccount = billingAccount;
  }

  public ProductOrderCreate productOrderItem(List<ProductOrderItem> productOrderItem) {
    this.productOrderItem = productOrderItem;
    return this;
  }

  public ProductOrderCreate addProductOrderItemItem(ProductOrderItem productOrderItemItem) {
    this.productOrderItem.add(productOrderItemItem);
    return this;
  }

   /**
   * Get productOrderItem
   * @return productOrderItem
  **/
  @Schema(required = true, description = "")
  public List<ProductOrderItem> getProductOrderItem() {
    return productOrderItem;
  }

  public void setProductOrderItem(List<ProductOrderItem> productOrderItem) {
    this.productOrderItem = productOrderItem;
  }

  public ProductOrderCreate relatedPublicKey(List<RelatedPublicKey> relatedPublicKey) {
    this.relatedPublicKey = relatedPublicKey;
    return this;
  }

  public ProductOrderCreate addRelatedPublicKeyItem(RelatedPublicKey relatedPublicKeyItem) {
    if (this.relatedPublicKey == null) {
      this.relatedPublicKey = new ArrayList<>();
    }
    this.relatedPublicKey.add(relatedPublicKeyItem);
    return this;
  }

   /**
   * Get relatedPublicKey
   * @return relatedPublicKey
  **/
  @Schema(description = "")
  public List<RelatedPublicKey> getRelatedPublicKey() {
    return relatedPublicKey;
  }

  public void setRelatedPublicKey(List<RelatedPublicKey> relatedPublicKey) {
    this.relatedPublicKey = relatedPublicKey;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductOrderCreate productOrderCreate = (ProductOrderCreate) o;
    return Objects.equals(this.category, productOrderCreate.category) &&
        Objects.equals(this.description, productOrderCreate.description) &&
        Objects.equals(this.externalId, productOrderCreate.externalId) &&
        Objects.equals(this.relatedParty, productOrderCreate.relatedParty) &&
        Objects.equals(this.channel, productOrderCreate.channel) &&
        Objects.equals(this.billingAccount, productOrderCreate.billingAccount) &&
        Objects.equals(this.productOrderItem, productOrderCreate.productOrderItem) &&
        Objects.equals(this.relatedPublicKey, productOrderCreate.relatedPublicKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, description, externalId, relatedParty, channel, billingAccount, productOrderItem, relatedPublicKey);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOrderCreate {\n");
    
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
    sb.append("    channel: ").append(toIndentedString(channel)).append("\n");
    sb.append("    billingAccount: ").append(toIndentedString(billingAccount)).append("\n");
    sb.append("    productOrderItem: ").append(toIndentedString(productOrderItem)).append("\n");
    sb.append("    relatedPublicKey: ").append(toIndentedString(relatedPublicKey)).append("\n");
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

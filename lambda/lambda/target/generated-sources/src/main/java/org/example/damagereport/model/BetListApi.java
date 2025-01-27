/*
 * ClaimParts DamageReportService
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.0.1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.example.damagereport.model;

import java.util.Objects;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.example.damagereport.model.BetApi;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * BetListApi
 */
@JsonPropertyOrder({
  BetListApi.JSON_PROPERTY_START_ELEMENT,
  BetListApi.JSON_PROPERTY_TOTAL_COUNT,
  BetListApi.JSON_PROPERTY_COUNT,
  BetListApi.JSON_PROPERTY_RESULTS
})
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-09-03T10:06:59.605457900+02:00[Europe/Belgrade]")
public class BetListApi {
  public static final String JSON_PROPERTY_START_ELEMENT = "startElement";
  private Long startElement;

  public static final String JSON_PROPERTY_TOTAL_COUNT = "totalCount";
  private Long totalCount;

  public static final String JSON_PROPERTY_COUNT = "count";
  private Long count;

  public static final String JSON_PROPERTY_RESULTS = "results";
  private List<BetApi> results = null;


  public BetListApi startElement(Long startElement) {
    this.startElement = startElement;
    return this;
  }

   /**
   * Get startElement
   * @return startElement
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_START_ELEMENT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getStartElement() {
    return startElement;
  }


  public void setStartElement(Long startElement) {
    this.startElement = startElement;
  }


  public BetListApi totalCount(Long totalCount) {
    this.totalCount = totalCount;
    return this;
  }

   /**
   * Get totalCount
   * @return totalCount
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_TOTAL_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getTotalCount() {
    return totalCount;
  }


  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }


  public BetListApi count(Long count) {
    this.count = count;
    return this;
  }

   /**
   * Get count
   * @return count
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Long getCount() {
    return count;
  }


  public void setCount(Long count) {
    this.count = count;
  }


  public BetListApi results(List<BetApi> results) {
    this.results = results;
    return this;
  }

  public BetListApi addResultsItem(BetApi resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<>();
    }
    this.results.add(resultsItem);
    return this;
  }

   /**
   * Get results
   * @return results
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_RESULTS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<BetApi> getResults() {
    return results;
  }


  public void setResults(List<BetApi> results) {
    this.results = results;
  }


  /**
   * Return true if this BetList object is equal to o.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BetListApi betList = (BetListApi) o;
    return Objects.equals(this.startElement, betList.startElement) &&
        Objects.equals(this.totalCount, betList.totalCount) &&
        Objects.equals(this.count, betList.count) &&
        Objects.equals(this.results, betList.results);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startElement, totalCount, count, results);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BetListApi {\n");
    sb.append("    startElement: ").append(toIndentedString(startElement)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}


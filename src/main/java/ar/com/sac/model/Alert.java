package ar.com.sac.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {
   
   @Id
   private String id;
   private Boolean active;
   private String expression;
   private String name;
   private String description;
   private Boolean sendEmail;
   private String symbol;
   private String opposedAlertId;
   private Boolean alarming;

}

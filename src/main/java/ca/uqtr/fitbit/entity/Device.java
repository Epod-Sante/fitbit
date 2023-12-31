package ca.uqtr.fitbit.entity;

import ca.uqtr.fitbit.entity.fitbit.Auth;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "device", schema = "public")
public class Device extends BaseEntity {


    @Column(name = "device_code", nullable = false)
    private String deviceCode;
    @Column(name = "device_version", nullable = false)
    private String deviceVersion;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "last_sync_date")
    private Timestamp lastSyncDate;
    @Column(name = "authorized", nullable = false)
    private boolean authorized;
    @Column(name = "admin_id", nullable = false)
    private UUID adminId;
    @Column(name = "available", nullable = false)
    private boolean available;
    @Column(name = "institution_code", nullable = false)
    private UUID institutionCode;
    @Column(name = "fitbit_profile_name", nullable = false)
    private String fitbitProfileName;
    @JsonManagedReference
    @OneToOne(mappedBy="device", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Auth auth;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "device_id")
    private List<PatientDevice> patientDevices;
    /*@ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "device_id")
    private List<FitbitSubscription> fitbitSubscriptions;*/
    @JsonManagedReference
    @OneToOne(mappedBy="device", cascade = CascadeType.ALL, orphanRemoval = true)
    private FitbitSubscription fitbitSubscription;
}

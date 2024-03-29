/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/domain/Entity.e.vm.java
 */
package fr.jaxio.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.google.common.base.Objects;

import fr.jaxio.audit.AuditContextHolder;

@Entity
@Table(name = "ACCOUNT")
@FilterDef(name = "myAccountFilter", defaultCondition = "ID = :currentAccountId ", parameters = @ParamDef(name = "currentAccountId", type = "org.hibernate.type.StringType"))
@Filter(name = "myAccountFilter")
@Indexed
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class Account implements Identifiable<String>, Serializable, Copyable<Account> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(Account.class);

	// Raw attributes
	private String id; // pk
	private String username; // unique (not null)
	private String password; // not null
	private String email; // unique (not null)
	private Boolean isEnabled; // not null
	private Civility civility;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String description;
	private Date creationDate;
	private String creationAuthor;
	private Date lastModificationDate;
	private String lastModificationAuthor;
	private Integer version;

	// Many to one
	private Address homeAddress; // (addressId)

	// One to many
	private List<Book> coolBooks = new ArrayList<Book>();
	private List<Document> edocs = new ArrayList<Document>();

	// Many to many
	private List<Role> securityRoles = new ArrayList<Role>();

	// -------------------------------
	// Role names support
	// -------------------------------

	/**
	 * Returns the granted authorities for this user. You may override this method to provide your own custom authorities.
	 */
	@Transient
	@XmlTransient
	public List<String> getRoleNames() {
		List<String> roleNames = new ArrayList<String>();

		for (Role securityRole : getSecurityRoles()) {
			roleNames.add(securityRole.getRoleName());
		}

		return roleNames;
	}

	// -- [id] ------------------------

	@Override
	@Column(name = "ID", length = 36)
	@GeneratedValue(generator = "strategy-uuid2")
	@GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
	@Id
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Account id(String id) {
		setId(id);
		return this;
	}

	@Override
	@Transient
	@XmlTransient
	@JsonIgnore
	public boolean isIdSet() {
		return id != null && !id.isEmpty();
	}

	// -- [username] ------------------------

	@Size(min = 4, max = 100)
	@NotEmpty
	@Column(name = "LOGIN", nullable = false, unique = true, length = 100)
	@Field(analyzer = @Analyzer(definition = "custom"))
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Account username(String username) {
		setUsername(username);
		return this;
	}

	// -- [password] ------------------------

	@Size(max = 100)
	@NotEmpty
	@Column(name = "`PASSWORD`", nullable = false, length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account password(String password) {
		setPassword(password);
		return this;
	}

	// -- [email] ------------------------

	@Size(max = 100)
	@NotEmpty
	@Email
	@Column(name = "EMAIL", nullable = false, unique = true, length = 100)
	@Field(analyzer = @Analyzer(definition = "custom"))
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account email(String email) {
		setEmail(email);
		return this;
	}

	// -- [isEnabled] ------------------------

	@NotNull
	@Column(name = "IS_ENABLED", nullable = false, length = 1)
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Account isEnabled(Boolean isEnabled) {
		setIsEnabled(isEnabled);
		return this;
	}

	// -- [civility] ------------------------

	@Column(name = "CIVILITY", length = 2)
	@Enumerated(STRING)
	public Civility getCivility() {
		return civility;
	}

	public void setCivility(Civility civility) {
		this.civility = civility;
	}

	public Account civility(Civility civility) {
		setCivility(civility);
		return this;
	}

	// -- [firstName] ------------------------

	@Size(max = 100)
	@Column(name = "FIRST_NAME", length = 100)
	@Field(analyzer = @Analyzer(definition = "custom"))
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Account firstName(String firstName) {
		setFirstName(firstName);
		return this;
	}

	// -- [lastName] ------------------------

	@Size(max = 100)
	@Column(name = "LAST_NAME", length = 100)
	@Field(analyzer = @Analyzer(definition = "custom"))
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Account lastName(String lastName) {
		setLastName(lastName);
		return this;
	}

	// -- [birthDate] ------------------------

	@Column(name = "BIRTH_DATE", length = 23)
	@Temporal(TIMESTAMP)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Account birthDate(Date birthDate) {
		setBirthDate(birthDate);
		return this;
	}

	// -- [description] ------------------------

	@Size(max = 255)
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account description(String description) {
		setDescription(description);
		return this;
	}

	// -- [creationDate] ------------------------

	@Column(name = "CREATION_DATE", length = 23)
	@Temporal(TIMESTAMP)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Account creationDate(Date creationDate) {
		setCreationDate(creationDate);
		return this;
	}

	// -- [creationAuthor] ------------------------

	@Column(name = "CREATION_AUTHOR", length = 200)
	public String getCreationAuthor() {
		return creationAuthor;
	}

	public void setCreationAuthor(String creationAuthor) {
		this.creationAuthor = creationAuthor;
	}

	public Account creationAuthor(String creationAuthor) {
		setCreationAuthor(creationAuthor);
		return this;
	}

	// -- [lastModificationDate] ------------------------

	@Column(name = "LAST_MODIFICATION_DATE", length = 23)
	@Temporal(TIMESTAMP)
	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public Account lastModificationDate(Date lastModificationDate) {
		setLastModificationDate(lastModificationDate);
		return this;
	}

	// -- [lastModificationAuthor] ------------------------

	@Column(name = "LAST_MODIFICATION_AUTHOR", length = 200)
	public String getLastModificationAuthor() {
		return lastModificationAuthor;
	}

	public void setLastModificationAuthor(String lastModificationAuthor) {
		this.lastModificationAuthor = lastModificationAuthor;
	}

	public Account lastModificationAuthor(String lastModificationAuthor) {
		setLastModificationAuthor(lastModificationAuthor);
		return this;
	}

	// -- [version] ------------------------

	@Column(name = "VERSION", precision = 10)
	@Version
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Account version(Integer version) {
		setVersion(version);
		return this;
	}

	// -----------------------------------------------------------------
	// Many to One support
	// -----------------------------------------------------------------

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// many-to-one: Account.addressId ==> Address.id
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	@JoinColumn(name = "ADDRESS_ID")
	@ManyToOne(cascade = { PERSIST, MERGE }, fetch = LAZY)
	public Address getHomeAddress() {
		return homeAddress;
	}

	@Transient
	public Integer getHomeAddressId() {
		return homeAddress == null ? null : homeAddress.getId();
	}

	@Transient
    public void setHomeAddressId(Integer id) {
		if (homeAddress ==  null) {
			this.homeAddress = new Address().id(id);
		} else {
			homeAddress.id(id);
		}
    }

	/**
	 * Set the {@link #homeAddress} without adding this Account instance on the passed {@link #homeAddress} If you want to preserve referential integrity we
	 * recommend to use instead the corresponding adder method provided by {@link Address}
	 */
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Account homeAddress(Address homeAddress) {
		setHomeAddress(homeAddress);
		return this;
	}

	// -----------------------------------------------------------------
	// One to Many support
	// -----------------------------------------------------------------

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// one to many: account ==> coolBooks
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	@OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = ALL)
	public List<Book> getCoolBooks() {
		return coolBooks;
	}

	/**
	 * Set the {@link Book} list. It is recommended to use the helper method {@link #addBook(Book)} / {@link #removeBook(Book)} if you want to preserve
	 * referential integrity at the object level.
	 * 
	 * @param coolBooks
	 *            the list to set
	 */
	public void setCoolBooks(List<Book> coolBooks) {
		this.coolBooks = coolBooks;
	}

	/**
	 * Helper method to add the passed {@link Book} to the {@link #coolBooks} list and set this owner on the passed book to preserve referential integrity at
	 * the object level.
	 * 
	 * @param book
	 *            the to add
	 * @return true if the book could be added to the coolBooks list, false otherwise
	 */
	public boolean addBook(Book book) {
		if (getCoolBooks().add(book)) {
			book.setOwner(this);
			return true;
		}
		return false;
	}

	/**
	 * Helper method to remove the passed {@link Book} from the {@link #coolBooks} list and unset this owner from the passed book to preserve referential
	 * integrity at the object level.
	 * 
	 * @param book
	 *            the instance to remove
	 * @return true if the book could be removed from the coolBooks list, false otherwise
	 */
	public boolean removeBook(Book book) {
		if (getCoolBooks().remove(book)) {
			book.setOwner(null);
			return true;
		}
		return false;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// one to many: account ==> edocs
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	@OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = ALL)
	public List<Document> getEdocs() {
		return edocs;
	}

	/**
	 * Set the {@link Document} list. It is recommended to use the helper method {@link #addEdoc(Document)} / {@link #removeEdoc(Document)} if you want to
	 * preserve referential integrity at the object level.
	 * 
	 * @param edocs
	 *            the list to set
	 */
	public void setEdocs(List<Document> edocs) {
		this.edocs = edocs;
	}

	/**
	 * Helper method to add the passed {@link Document} to the {@link #edocs} list and set this owner on the passed edoc to preserve referential integrity at
	 * the object level.
	 * 
	 * @param edoc
	 *            the to add
	 * @return true if the edoc could be added to the edocs list, false otherwise
	 */
	public boolean addEdoc(Document edoc) {
		if (getEdocs().add(edoc)) {
			edoc.setOwner(this);
			return true;
		}
		return false;
	}

	/**
	 * Helper method to remove the passed {@link Document} from the {@link #edocs} list and unset this owner from the passed edoc to preserve referential
	 * integrity at the object level.
	 * 
	 * @param edoc
	 *            the instance to remove
	 * @return true if the edoc could be removed from the edocs list, false otherwise
	 */
	public boolean removeEdoc(Document edoc) {
		if (getEdocs().remove(edoc)) {
			edoc.setOwner(null);
			return true;
		}
		return false;
	}

	// -----------------------------------------------------------------
	// Many to Many
	// -----------------------------------------------------------------

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// many-to-many: account ==> securityRoles
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	/**
	 * Returns the {@link #securityRoles} list.
	 */
	@JoinTable(name = "ACCOUNT_ROLE", joinColumns = @JoinColumn(name = "ACCOUNT_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@ManyToMany(cascade = { PERSIST, MERGE })
	public List<Role> getSecurityRoles() {
		return securityRoles;
	}

	/**
	 * Set the {@link #securityRoles} list.
	 * <p>
	 * It is recommended to use the helper method {@link #addSecurityRole(Role)} / {@link #removeSecurityRole(Role)} if you want to preserve referential
	 * integrity at the object level.
	 * 
	 * @param securityRoles
	 *            the list of Role
	 */
	public void setSecurityRoles(List<Role> securityRoles) {
		this.securityRoles = securityRoles;
	}

	/**
	 * Helper method to add the passed {@link Role} to the {@link #securityRoles} list.
	 */
	public boolean addSecurityRole(Role securityRole) {
		return getSecurityRoles().add(securityRole);
	}

	/**
	 * Helper method to remove the passed {@link Role} from the {@link #securityRoles} list.
	 */
	public boolean removeSecurityRole(Role securityRole) {
		return getSecurityRoles().remove(securityRole);
	}

	/**
	 * Helper method to determine if the passed {@link Role} is present in the {@link #securityRoles} list.
	 */
	public boolean containsSecurityRole(Role securityRole) {
		return getSecurityRoles() != null && getSecurityRoles().contains(securityRole);
	}

	/**
	 * Set the default values.
	 */
	public void initDefaultValues() {
	}

	/**
	 * Equals implementation using a business key.
	 */
	@Override
	public boolean equals(Object other) {
		return this == other || (other instanceof Account && hashCode() == other.hashCode());
	}

	private volatile int previousHashCode = 0;

	@Override
	public int hashCode() {
		int hashCode = Objects.hashCode(getUsername());
		if (previousHashCode != 0 && previousHashCode != hashCode) {
			log.warn("DEVELOPER: hashCode has changed!." //
					+ "If you encounter this message you should take the time to carefuly review equals/hashCode for: " //
					+ getClass().getCanonicalName());
		}

		previousHashCode = hashCode;
		return hashCode;
	}

	/**
	 * Construct a readable string representation for this Account instance.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this) //
				.add(Account_.id.getName(), getId()) //
				.add(Account_.username.getName(), getUsername()) //
				.add(Account_.password.getName(), getPassword()) //
				.add(Account_.email.getName(), getEmail()) //
				.add(Account_.isEnabled.getName(), getIsEnabled()) //
				.add(Account_.civility.getName(), getCivility()) //
				.add(Account_.firstName.getName(), getFirstName()) //
				.add(Account_.lastName.getName(), getLastName()) //
				.add(Account_.birthDate.getName(), getBirthDate()) //
				.add(Account_.description.getName(), getDescription()) //
				.add(Account_.creationDate.getName(), getCreationDate()) //
				.add(Account_.creationAuthor.getName(), getCreationAuthor()) //
				.add(Account_.lastModificationDate.getName(), getLastModificationDate()) //
				.add(Account_.lastModificationAuthor.getName(), getLastModificationAuthor()) //
				.add(Account_.version.getName(), getVersion()) //
				.toString();
	}

	/**
	 * Return a copy of the current object
	 */
	@Override
	@Transient
	@XmlTransient
	public Account copy() {
		Account account = new Account();
		copyTo(account);
		return account;
	}

	/**
	 * Copy the current properties to the given object
	 */
	@Override
	@Transient
	@XmlTransient
	public void copyTo(Account account) {
		account.setId(getId());
		account.setUsername(getUsername());
		account.setPassword(getPassword());
		account.setEmail(getEmail());
		account.setIsEnabled(getIsEnabled());
		account.setCivility(getCivility());
		account.setFirstName(getFirstName());
		account.setLastName(getLastName());
		account.setBirthDate(getBirthDate());
		account.setDescription(getDescription());
		// account.setAddressId(getAddressId());
		account.setCreationDate(getCreationDate());
		account.setCreationAuthor(getCreationAuthor());
		account.setLastModificationDate(getLastModificationDate());
		account.setLastModificationAuthor(getLastModificationAuthor());
		account.setVersion(getVersion());
		if (getHomeAddress() != null) {
			account.setHomeAddress(new Address().id(getHomeAddress().getId()));
		}
	}

	@PrePersist
	protected void prePersist() {
		if (AuditContextHolder.audit()) {
			setCreationAuthor(AuditContextHolder.username());
			setCreationDate(new Date());
		}
	}

	@PreUpdate
	protected void preUpdate() {
		if (AuditContextHolder.audit()) {
			setLastModificationAuthor(AuditContextHolder.username());
			setLastModificationDate(new Date());
		}
	}
}
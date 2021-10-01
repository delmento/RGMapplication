package com.bludots.app.rgm.password.registration.repositories.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.envers.Audited;


	@Audited
	@Entity
	@Table(name = "table-role")
	public class Role {
		@TableGenerator(name = "table", table = "sequences_", pkColumnName = "PK_NAME", valueColumnName = "PK_VALUE", initialValue = 1000000)
		@GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
		@Id
		@Column(name = "ID_ROLE_")
		private Long id;

		@Column(name = "RoleDescription_")
		private String roledescription;
		
		@ManyToMany (mappedBy = "roles")
		private Set<User> users;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getRoledescription() {
			return roledescription;
		}

		public void setRoledescription(String roledescription) {
			this.roledescription = roledescription;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((roledescription == null) ? 0 : roledescription.hashCode());
			result = prime * result + ((users == null) ? 0 : users.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Role other = (Role) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (roledescription == null) {
				if (other.roledescription != null)
					return false;
			} else if (!roledescription.equals(other.roledescription))
				return false;
			if (users == null) {
				if (other.users != null)
					return false;
			} else if (!users.equals(other.users))
				return false;
			return true;
		}

		private Role getEnclosingInstance() {
			return Role.this;
		}

	}

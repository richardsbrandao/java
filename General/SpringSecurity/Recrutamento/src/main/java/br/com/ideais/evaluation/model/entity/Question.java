package br.com.ideais.evaluation.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.com.ideais.evaluation.helpers.Level;
import br.com.ideais.evaluation.helpers.QuestionStatus;

@Entity
public class Question {
	
	@Id
	@SequenceGenerator(name = "question_id", sequenceName = "question_id")
	@GeneratedValue(generator = "question_id", strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String description;
	@OneToMany(mappedBy = "question")
	@Cascade({CascadeType.ALL})
	private List<Option> options;
	@Enumerated(EnumType.STRING)
	private QuestionStatus status;
	@Enumerated(EnumType.STRING)
	private Level level;
	
	public void setCorrectOption(Integer correctAnswer) {
		if(getOption(correctAnswer) != null) {
			getOption(correctAnswer).setCorrect(true);
		}
	}
	public Option getCorrectOption() {
		for(Option option : options) {
			if(option.isCorrect()) {
				return option;
			}
		}
		return null;
	}
	public Boolean hasOptions() {
		return options != null && !options.isEmpty();
	}
	public Option getOption(Integer optionIndex) {
		return options.size() > optionIndex ? options.get(optionIndex) : null;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	public QuestionStatus getStatus() {
		return status;
	}
	public void setStatus(QuestionStatus status) {
		this.status = status;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Question other = (Question) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
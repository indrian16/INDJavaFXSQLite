package indrian16.outlook.co.id.javafxsqlite;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

public class ValidationTextField extends TextField {
	
	private final BooleanProperty invalid = new SimpleBooleanProperty();
	private final StringProperty  mask;
	private final IntegerProperty minLength;
	private final IntegerProperty maxLength;
	
	private Effect invalidEffect = new DropShadow(BlurType.GAUSSIAN, Color.RED, 4, 0.0, 0, 0);
	
	public ValidationTextField() {
		super();
		
		this.mask = new SimpleStringProperty(".");
		this.minLength = new SimpleIntegerProperty(-1);
		this.maxLength = new SimpleIntegerProperty(-2);
		
		bind();
	}
	
	public ValidationTextField(String mask, int minLength, int maxLength, boolean nullable) {
		
		this(mask, minLength, maxLength, nullable, null);
	}
	
	public ValidationTextField(String mask, int minLength, int maxLength, boolean nullable, String string) {
		super(string);
		
		this.mask = new SimpleStringProperty(mask);
		this.minLength = new SimpleIntegerProperty(minLength);
		this.maxLength = new SimpleIntegerProperty(maxLength);
		
		bind();
	}
	
	
	
	public ReadOnlyBooleanProperty invalidProperty() {
		
		return invalid;
	}
	
	public ReadOnlyStringProperty maskProperty() {
		
		return mask;
	}
	
	public ReadOnlyIntegerProperty minLengthProperty() {
		
		return minLength;
	}
	
	public ReadOnlyIntegerProperty maxLengthProperty() {
		
		return maxLength;
	}
	
	public Boolean getInvalid() {
		
		return invalid.get();
	}
	
	public String getMask() {
		
		return mask.get();
	}
	
	public void setMask(String mask) {
		
		this.mask.set(mask);
	}
	
	public int getMinLenght() {
		
		return minLength.get();
	}
	
	public void setMinLength(int minLength) {
		
		this.minLength.set(minLength);
	}
	
	public int getMaxLength() {
		
		return maxLength.get();
	}
	
	public void setMaxLength(int maxLength) {
		
		this.maxLength.set(maxLength);
	}
	
	public Effect getInvalidEffect() {
		
		return this.invalidEffect;
	}
	
	public void setInvalidEffect(Effect effect) {
		
		this.invalidEffect = effect;
	}
	
	
	private void bind() {
		
		this.invalid.bind(maskCheck().or(minLengthCheck()));
		
		this.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> ov, String t1, String t2) {
				
				if (textProperty().get().length() > maxLength.get()) {
					
					setText(t1);
				}
			}
			
		});
	}
	
	public BooleanBinding maskCheck() {
		
		return new BooleanBinding() {

			@Override
			protected boolean computeValue() {
				return !textProperty().get().matches(mask.get());
			}
			
		};
	}
	
	private BooleanBinding minLengthCheck() {
		
		return new BooleanBinding() {

			@Override
			protected boolean computeValue() {
				return textProperty().get().length() < minLength.get();
			}
			
		};
	}
	
	private BooleanBinding maxLengthCheck() {
		
		return new BooleanBinding() {

			@Override
			protected boolean computeValue() {
				return textProperty().get().length() > maxLength.get();
			}
			
			
		};
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

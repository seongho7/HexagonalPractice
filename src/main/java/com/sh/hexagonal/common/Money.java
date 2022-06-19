package com.sh.hexagonal.common;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Value
public class Money {

	public static Money ZERO = Money.of(0L);

	@NonNull BigDecimal amount;

	public boolean isGreaterThanOrEqualTo(Money money){
		return this.amount.compareTo(money.amount) >= 0;
	}

	public boolean isGreaterThan(Money money){
		return this.amount.compareTo(money.amount) >= 1;
	}

	public static Money of(long value) {
		return new Money(BigDecimal.valueOf(value));
	}

	public static Money add(Money a, Money b) {
		return new Money(a.amount.add(b.amount));
	}

	public Money minus(Money money){
		return new Money(this.amount.subtract(money.amount));
	}

	public Money plus(Money money){
		return new Money(this.amount.add(money.amount));
	}

	public static Money subtract(Money a, Money b) {
		return new Money(a.amount.subtract(b.amount));
	}

	public Money negate(){
		return new Money(this.amount.negate());
	}

	public Money multiply(long val) {
		return new Money(this.amount.multiply(BigDecimal.valueOf(val)));
	}

	public Money multiply(double val) {
		return new Money(this.amount.multiply(BigDecimal.valueOf(val)));
	}

	public Money divide(long val) {
		return new Money(this.amount.divide(BigDecimal.valueOf(val)));
	}

	public Money floor() {
		return new Money(this.amount.setScale(0, RoundingMode.FLOOR));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Money money = (Money) o;
		return amount.equals(money.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}
}

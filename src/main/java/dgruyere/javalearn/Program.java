package dgruyere.javalearn;

import java.util.Scanner;

/*
* Условие тестового задания можно найти здесь:
* https://jm-program.github.io/task-calculator
* */

public class Program {
	public static Boolean isRoman;

	public static void main(String[] args) throws CalculationException {
		Scanner input = new Scanner(System.in);
		try {
			Integer firstDigit = getInt(input.next());
			Boolean isRomanCheck = isRoman;
			String operator = input.next();
			Integer secondDigit = getInt(input.next());
			if (firstDigit > 10 || firstDigit < 1 || secondDigit > 10 || secondDigit < 1)
				throw new CalculationException("Input number must be bigger than 0 and less than 11");
			if (isRoman != isRomanCheck)
				throw new CalculationException("Only arabic or only roman numerals in same input supported");
			if (isRoman)
				System.out.println(Roman.intToRoman(calculate(firstDigit, secondDigit, operator)));
			else
				System.out.println(calculate(firstDigit, secondDigit, operator));
		} catch (IllegalArgumentException e) {
			throw new CalculationException("Only arabic or only roman numerals in same input supported");
		}
	}

	/*
	* Получаем число из строки (не важно, какие именно цифры в строке: римские или арабские).
	* */
	public static Integer getInt(String inputFirstDigit) {
		if (Character.isDigit(inputFirstDigit.charAt(0))) {
			isRoman = false;
			return (Integer.valueOf(inputFirstDigit));
		} else {
			isRoman = true;
			return (Roman.valueOf(inputFirstDigit).toInt());
		}
	}

	/*
	* Вычисляем результат
	* */
	public static Integer calculate(Integer firstDigit, Integer secondDigit, String operator) {
		return switch (operator) {
			case "+" -> (firstDigit + secondDigit);
			case "-" -> (firstDigit - secondDigit);
			case "*" -> (firstDigit * secondDigit);
			case "/" -> (firstDigit / secondDigit);
			default -> (0);
		};
	}
}

/*
* Работа с римскими цифрами находится только в этом классе.
* */
 enum Roman {
	I(1), II(2), III(3), IV(4), V(5),
	VI(6), VII(7), VIII(8), IX(9), X(10);
	private final int value;

	Roman(int value) {
		this.value = value;
	}

	/*
	 * Конвертируем римское число в арабское (в диапазоне [1, 10]).
	 * */
	public int toInt() {
		return value;
	}

	/*
	* Конвертируем арабское число в римское (В диапазоне [1,..,90,100]).
	* */
	static String intToRoman(Integer num)
	{
		String[] x = {"", "X", "XX", "XXX", "XL", "L",
				 "LX", "LXX", "LXXX", "XC"};
		String[] i = {"", "I", "II", "III", "IV", "V",
				 "VI", "VII", "VIII", "IX"};

		if (num == 100)
			return ("C");
		String tens = x[(num % 100) / 10];
		String ones = i[num % 10];

		return tens + ones;
	}
}

/*
* Кастомная ошибка.
* */
class CalculationException extends Exception {
	public CalculationException(String errorMessage) {
		super(errorMessage);
	}
}
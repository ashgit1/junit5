package com.ashish;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("When Running MathUtils")
class MathsUtilsTest {

	MathsUtils mathutils;
	static int instanceCounter;
	TestInfo testInfo;
	TestReporter testReporter;

	@BeforeAll
	void beforeAllInit() {
		System.out.println("This needs to be run Before All Init...");
	}

	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		mathutils = new MathsUtils();
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		//++instanceCounter;
		//System.out.println("MathsUtil Instance is created: " + instanceCounter);
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
	}

	@AfterEach
	void cleanUp() {
		System.out.println("Cleaning Up...");
	}

	@AfterAll
	void afterAllInit() {
		System.out.println("This needs to be run After All Init...");
	}

	@Nested
	@DisplayName("add Methods")
	@Tag("Math")
	class addTest {

		@Test
		@DisplayName("when adding 2 positive numbers")
		void testAddPositive() {
			assertEquals(2, mathutils.add(1, 1), "returns the right sum");
		}

		@Test
		@DisplayName("when adding 2 negative numbers")
		void testAddNegative() {
			assertEquals(-2, mathutils.add(-1, -1), "returns the right sum");
		}
	}

	@Test
	@Tag("Math")
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathutils.divide(1, 0), "Divide by Zero should Occur");
	}

	@RepeatedTest(3)
	void computeCircleArea() {
		assertEquals(314.1592653589793, mathutils.computeCircleArea(10), "Should return right circle area");
	}

	@DisplayName("TDD method. Should not run")
	@Disabled
	@Test
	void testDisabled() {
		fail("This test should be disabled");
	}

	@EnabledOnOs(OS.LINUX)
	@Test
	void testLinux() {
		fail("This test should be disabled");
	}

	@Test
	void runTestWhenServerisUp() {
		boolean serverCheck = false;
		assumeTrue(serverCheck);
		System.out.println("Server is up");
	}

	@Test
	@DisplayName("mutiple Method")
	@Tag("Math")
	void testMultiply() {
		assertAll(() -> assertEquals(4, mathutils.multiply(2, 2)), () -> assertEquals(0, mathutils.multiply(2, 0)),
				() -> assertEquals(-2, mathutils.multiply(2, -1)));
	}
}
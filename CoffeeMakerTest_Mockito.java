package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;



//Unit tests for CoffeeMaker class.

public class CoffeeMakerTest {
	
	//-----------------------------------------------------------------------
	//	DATA MEMBERS
	//-----------------------------------------------------------------------
	
	private Recipe recipe1;
	
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	
	private Recipe [] stubRecipies; 
	
	private CoffeeMaker coffeeMaker;
	
	private RecipeBook recipeBookStub;
	
	@Before
	public void setUp() throws RecipeException {
		Inventory inv = new Inventory();
		recipeBookStub = mock(RecipeBook.class);
		coffeeMaker = new CoffeeMaker(recipeBookStub, inv);
		
		//Set up for recipe1
		
		recipe1 = spy(new Recipe());
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("1");
		recipe1.setAmtCoffee("2");
		recipe1.setAmtMilk("3");
		recipe1.setAmtSugar("4");
		recipe1.setPrice("50");
		
		//Set up for recipe2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("30");
		recipe2.setAmtMilk("19");
		recipe2.setAmtSugar("18");
		recipe2.setPrice("75");
		
		//Set up for recipe3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");


		stubRecipies = new Recipe [] {recipe1, recipe2, recipe3};
		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);		
	}
	
	@Test
	public void testAddInventoryWithStringVal() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		try {
			coffeeMaker.addInventory("sdf", "ifgjdi", "asdf", "sdfdgfgh");
			assertTrue(true);
		} catch (InventoryException e) {			
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithStringVal1() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		try {
			coffeeMaker.addInventory("-44", "4", "6", "3");
			assertTrue(true);
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithStringVal2() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		try {
			coffeeMaker.addInventory("44", "-4", "6", "3");
			assertTrue(true);
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithStringVal3() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		try {
			coffeeMaker.addInventory("44", "4", "-6", "3");
			assertTrue(true);
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithStringVal4() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		try {
			coffeeMaker.addInventory("44", "4", "6", "-33");
			assertTrue(true);
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithStringVal5() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		try {
			coffeeMaker.addInventory("4", "ifgjdi", "asdf", "sdfdgfgh");
			assertTrue(true);
		} catch (InventoryException e) {			
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithStringVal6() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		try {
			coffeeMaker.addInventory("4", "4", "asdf", "sdfdgfgh");
			assertTrue(true);
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithStringVal7() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		try {
			coffeeMaker.addInventory("4", "5", "5", "sdfdgfgh");
			assertTrue(true);
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithOutOfBound() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		try {
			coffeeMaker.addInventory("444444444444444444444444444444444444444444444444444444444444", "111111111111111111111111111111111111111111111", "11111111111111111111111222222222222222222222", "33333333333333333333333333333333334444444444444443");
			assertTrue(true);
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithWrongVal() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		try {
			coffeeMaker.addInventory("-55", "-10", "-19", "-9");
			assertTrue(true);
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	//ccoff,milk,sugar,choco
	@Test
	public void testAddInventoryWithValCheckCoffe() {
		Inventory inv= new Inventory();
		coffeeMaker = new CoffeeMaker(recipeBookStub, inv);
		
		try {
			coffeeMaker.addInventory("4", "9", "5", "3");
			assertEquals(19, inv.getCoffee());
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithValCheckMilk() {
		Inventory inv= new Inventory();
		coffeeMaker = new CoffeeMaker(recipeBookStub,inv);
		
		try {
			coffeeMaker.addInventory("4", "10", "5", "3");
			assertEquals(25, inv.getMilk());
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithValCheckSugar() {
		Inventory inv= new Inventory();
		coffeeMaker = new CoffeeMaker(recipeBookStub, inv);
		
		try {
			coffeeMaker.addInventory("4", "9", "5", "3");
			assertEquals(20, inv.getSugar());
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	@Test
	public void testAddInventoryWithValCheckChoco() {
		Inventory inv= new Inventory();
		coffeeMaker = new CoffeeMaker(recipeBookStub, inv);
		
		try {
			coffeeMaker.addInventory("4", "9", "5", "3");
			assertEquals(18, inv.getChocolate());
		} catch (InventoryException e) {
			assertFalse(false);
		}
	}
	@Test
	public void testCheckInventory1() {
		
	}
	
	@Test
	public	void testPurchaseWithRightAmmountToCheckChange() throws RecipeException {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
	
		assertEquals(0,coffeeMaker.makeCoffee(0, 50));
	}
	@Test
	public	void testPurchaseWithRightAmmountToCheckChange1() throws RecipeException {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		
		assertEquals(-5,coffeeMaker.makeCoffee(0, -5));
	}
	@Test
	public	void testPurchaseWithRightAmmountToCheckInventoryRemainings() throws RecipeException {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		coffeeMaker.makeCoffee(0, 50);
		assertEquals("Coffee: 13\n" + 
				"Milk: 12\n" + 
				"Sugar: 11\n" + 
				"Chocolate: 14\n",coffeeMaker.checkInventory());
	}
	@Test
	public	void testPurchaseWithRightAmmountToCheckInventoryRemainings1() throws RecipeException {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());	
		coffeeMaker.makeCoffee(0, -5);
		assertEquals("Coffee: 15\n" + 
				"Milk: 15\n" + 
				"Sugar: 15\n" + 
				"Chocolate: 15\n",coffeeMaker.checkInventory());
	}
	@Test
	public	void testPurchaseToCheckRecipeExists() throws RecipeException {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		assertEquals(5,coffeeMaker.makeCoffee(6, 5));
	}
	@Test
	public	void testPurchaseToCheckRecipeExists1() throws RecipeException {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		assertEquals(5,coffeeMaker.makeCoffee(0, 55));
	}
	
}

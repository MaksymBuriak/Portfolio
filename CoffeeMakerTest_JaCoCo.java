package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import junit.framework.TestCase;


//Unit tests for CoffeeMaker class

public class CoffeeMakerTest extends TestCase {
	
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	private Recipe recipe6;
	private Recipe recipe7;
	private CoffeeMaker coffeeMaker;
	private Inventory inventory;
	
	protected void setUp() throws Exception {
		
		coffeeMaker = new CoffeeMaker();
		inventory = new Inventory();
		
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("2");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
		
		//Set up for r5
		recipe5 = new Recipe();
		recipe5.setName("You Chocolate");
		recipe5.setAmtChocolate("12");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("10");
		recipe5.setAmtSugar("13");
		recipe5.setPrice("65");	
		
		//Set up for r6
		recipe6 = new Recipe();
		recipe6.setName("Espresso");
		recipe6.setAmtChocolate("0");
		recipe6.setAmtCoffee("20");
		recipe6.setAmtMilk("0");
		recipe6.setAmtSugar("0");
		recipe6.setPrice("40");		

		//Set up for r7
		recipe7 = new Recipe();
		recipe7.setName("Milkshake");
		recipe7.setAmtChocolate("1");
		recipe7.setAmtCoffee("0");
		recipe7.setAmtMilk("10");
		recipe7.setAmtSugar("15");
		recipe7.setPrice("50");		
		
		//Set up inventory
		inventory.setCoffee(15);
		inventory.setMilk(15);
		inventory.setSugar(15);
		inventory.setChocolate(15);
		
		super.setUp();
	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}
	
	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test //correct change returned after makeCoffee method use
	public void testMakeCoffee1() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}
	
	@Test //correct change returned after makeCoffee method use
	public void testMakeCoffee2() {
		coffeeMaker.addRecipe(recipe2);
		assertEquals(75, coffeeMaker.makeCoffee(0, 75));
	}
	
	@Test //correct change returned after makeCoffee method use
	public void testMakeCoffee3() {
		coffeeMaker.addRecipe(recipe3);
		assertEquals(0, coffeeMaker.makeCoffee(0, 100));
	}
	
	@Test //correct change returned after makeCoffee method use
	public void testMakeCoffee4() {
		coffeeMaker.addRecipe(recipe4);
		assertEquals(15, coffeeMaker.makeCoffee(0, 80));
	}
	@Test //(expected = assertNotEquals == true)
	public void testMakeCoffee5() {
		coffeeMaker.addRecipe(recipe5);
		assertNotEquals(20, coffeeMaker.makeCoffee(0, 75));
	}
	
	@Test //(expected = assertNotEquals == true)
	public void testMakeCoffee6() {
		coffeeMaker.addRecipe(recipe6);
		assertNotEquals(40, coffeeMaker.makeCoffee(0, 80));
	}
	
	@Test //correct change returned after makeCoffee method use
	public void testMakeCoffee7() {
		coffeeMaker.addRecipe(recipe7);
		assertEquals(30, coffeeMaker.makeCoffee(0, 80));
	}
	
	@Test //correct change returned after makeCoffee method use
	public void testMakeCoffee8() {
		coffeeMaker.addRecipe(recipe7);
		assertEquals(40, coffeeMaker.makeCoffee(0, 40));
	}
	
	@Test //correct change returned after makeCoffee method use
	public void testMakeCoffee9() {
		assertEquals(30, coffeeMaker.makeCoffee(0, 30));
	}
	
	@Test //issue detected - after useIngredients method: inventory.getCoffee() != Inventory.coffee -= r.getAmtCoffee();
	public void testUseIngredients1() {
		inventory.useIngredients(recipe1);
		assertEquals(13, inventory.getChocolate());
		assertEquals(14, inventory.getMilk());
		assertEquals(14, inventory.getSugar());
		assertEquals(12, inventory.getCoffee());
	}
	
	@Test //issue detected - after useIngredients method: inventory.getCoffee() != Inventory.coffee -= r.getAmtCoffee();
	public void testUseIngredients2() {
		inventory.useIngredients(recipe3);
		assertEquals(15, inventory.getChocolate());
		assertEquals(12, inventory.getMilk());
		assertEquals(14, inventory.getSugar());
		assertEquals(12, inventory.getCoffee());
	}
	
	@Test //test passed as coffee isn't used in the recipe
	public void testUseIngredients3() {
		inventory.useIngredients(recipe4);
		assertEquals(11, inventory.getChocolate());
		assertEquals(14, inventory.getMilk());
		assertEquals(14, inventory.getSugar());
		assertEquals(15, inventory.getCoffee());
	}
	
	@Test //issue detected - length of a RecipeBook array > 3, when should be able to only add three drinks to the menu
	public void testRecipeBookLength1() {	
		assertEquals(3, coffeeMaker.getRecipes().length);
	}

	@Test //issue detected - able to add more then three drinks to the menu
	public void testRecipeBookLength2() {	
		assertTrue(coffeeMaker.addRecipe(recipe1));
		assertTrue(coffeeMaker.addRecipe(recipe3));
		assertTrue(coffeeMaker.addRecipe(recipe4));
		assertFalse(coffeeMaker.addRecipe(recipe7));
	}

	@Test //correct checkInventory return
	public void testCheckInventory() {	
		coffeeMaker.checkInventory();
	}
	
	@Test //correct implementation of an editRecipe (recipe is present)
	public void test_editRecipe1() {	
		coffeeMaker.addRecipe(recipe7);
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.editRecipe(1, recipe4);
	}
	
	@Test //correct implementation of an editRecipe (recipe is present)
	public void test_editRecipe2() {	
		coffeeMaker.addRecipe(recipe7);
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.editRecipe(1, recipe3);
	}
	
	@Test //correct implementation of an editRecipe (recipe is present)
	public void test_editRecipe3() {	
		coffeeMaker.addRecipe(recipe7);
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.editRecipe(2, recipe4);
	}
	
	@Test //correct implementation of a deleteRecipe (recipe is present)
	public void test_deleteRecipe1() {	
		coffeeMaker.addRecipe(recipe7);
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.deleteRecipe(0);
	}
	
	@Test //correct implementation of a deleteRecipe (recipe is present)
	public void test_deleteRecipe2() {	
		coffeeMaker.addRecipe(recipe7);
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.deleteRecipe(1);
	}
	
	@Test //correct implementation of a deleteRecipe (recipe is present)
	public void test_deleteRecipe3() {	
		coffeeMaker.addRecipe(recipe7);
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.deleteRecipe(2);
	}
	
	@Test //correct implementation of a deleteRecipe (recipe is not present)
	public void test_deleteRecipe4() {	
		coffeeMaker.addRecipe(recipe7);
		assertEquals(null, coffeeMaker.deleteRecipe(2));
	}

}

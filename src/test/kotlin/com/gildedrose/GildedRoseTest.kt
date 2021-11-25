package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `On update, SellIn will be lowered by one`() {
        val items = arrayOf(Item("foo", 1, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].sellIn)
    }

    @Test
    fun `On update, Quality will be lowered by one`() {
        val items = arrayOf(Item("foo", 0, 1))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `Once the sell by date has passed, Quality degrades twice as fast`() {
        val items = arrayOf(Item("foo", -1, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `The Quality of an item is never negative`() {
        val items = arrayOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `Aged Brie actually increases in Quality the older it gets`() {
        val items = arrayOf(Item("Aged Brie", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(2, app.items[0].quality)
    }

    @Test
    fun `The Quality of an item is never more than 50`() {
        val items = arrayOf(Item("Aged Brie", 0, 49))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun `Sulfuras never has to be sold`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].sellIn)
    }

    @Test
    fun `Sulfuras never decreases in Quality`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(80, app.items[0].quality)
    }

    @Test
    fun `Backstage passes increase in Quality by 2 when there are 10 days or less`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(12, app.items[0].quality)
    }

    @Test
    fun `Backstage passes increase in Quality by 3 when there are 5 days or less`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(13, app.items[0].quality)
    }

    @Test
    fun `Backstage passes Quality drops to 0 after the concert`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", -1, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `Conjured items degrade in Quality twice as fast as normal items`() {
        val items = arrayOf(Item("Conjured Mana Cake", 5, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(8, app.items[0].quality)
    }

    @Test
    fun `Get Item String`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", -1, 10)
        assertEquals("Backstage passes to a TAFKAL80ETC concert, -1, 10", item.toString())
    }
}



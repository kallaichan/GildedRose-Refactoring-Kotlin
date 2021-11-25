package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            item.updateIndividualItem()
        }
    }

    private fun Item.updateIndividualItem() {
        if (isSulfuras()) return
        if (isAgedBrie()) return updateAgedBrie()
        if (isBackstagePass()) return updateBackstagePass()
        if (isConjuredItem()) return updateConjuredItem()

        updateNormalItem()
    }

    private fun Item.isSulfuras() = name.contains(ItemType().SULFURAS, ignoreCase = true)

    private fun Item.isAgedBrie() = name == ItemType().AGED_BRIE

    private fun Item.isBackstagePass() = name.contains(ItemType().BACKSTAGE_PASS, ignoreCase = true)

    private fun Item.isConjuredItem() = name.contains(ItemType().CONJURED, ignoreCase = true)

    private class ItemType {
        val AGED_BRIE = "Aged Brie"
        val BACKSTAGE_PASS = "Backstage pass"
        val SULFURAS = "Sulfuras"
        val CONJURED = "Conjured"
    }

    private fun Item.updateAgedBrie() {
        decrementSellIn()
        incrementQualityBy(2)
    }

    private fun Item.updateBackstagePass() {
        when (sellIn) {
            in Int.MIN_VALUE .. -1 -> quality = 0
            in 0..5 -> incrementQualityBy(3)
            in 6..10 -> incrementQualityBy(2)
            else -> incrementQualityBy(1)
        }
    }

    private fun Item.updateConjuredItem() {
        decrementSellIn()
        decrementQualityBy(2)
    }

    private fun Item.updateNormalItem() {
        decrementSellIn()
        if (sellIn < 0) {
            decrementQualityBy(2)
        } else {
            decrementQualityBy(1)
        }
    }

    private fun Item.decrementSellIn() {
        sellIn--
    }

    private fun Item.incrementQualityBy(delta: Int) {
        quality += delta
        quality = quality.coerceAtMost(50)
    }

    private fun Item.decrementQualityBy(delta: Int) {
        quality -= delta
        quality = quality.coerceAtLeast(0)
    }
}


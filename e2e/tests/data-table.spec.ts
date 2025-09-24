import { test, expect } from '@playwright/test';

test.describe('King Data Table Application', () => {
  test.beforeEach(async ({ page }) => {
    // Navigate to the application
    await page.goto('/');
    
    // Wait for the page to load
    await page.waitForLoadState('networkidle');
  });

  test('should load the application and display data table with data', async ({ page }) => {
    // Check page title
    await expect(page).toHaveTitle(/King/i);
    
    // Check that the data table exists
    const dataTable = page.locator('.data-table');
    await expect(dataTable).toBeVisible();
    
    // Check that table headers are present
    const table = page.locator('.data-table-grid');
    await expect(table).toBeVisible();
    
    // Verify all expected column headers exist
    await expect(page.locator('th:has-text("ID")')).toBeVisible();
    await expect(page.locator('th:has-text("Name")')).toBeVisible();
    await expect(page.locator('th:has-text("Status")')).toBeVisible();
    await expect(page.locator('th:has-text("Description")')).toBeVisible();
    await expect(page.locator('th:has-text("Delta")')).toBeVisible();
    await expect(page.locator('th:has-text("Created On")')).toBeVisible();
    
    // Wait for data to load and check that table has data rows
    await expect(page.locator('tbody tr')).toHaveCount(await page.locator('tbody tr').count());
    expect(await page.locator('tbody tr').count()).toBeGreaterThan(0);
    
    // Check that the first row has data (not loading or empty state)
    const firstDataRow = page.locator('tbody tr').first();
    await expect(firstDataRow).toBeVisible();
    
    // Verify that the first row contains actual data, not loading text
    await expect(firstDataRow).not.toContainText('Loading data...');
    await expect(firstDataRow).not.toContainText('No data found');
    
    // Check that status badges are rendered
    const statusBadges = page.locator('.status-badge');
    await expect(statusBadges.first()).toBeVisible();
    
    // Verify pagination info is shown
    const tableInfo = page.locator('.table-info');
    await expect(tableInfo).toBeVisible();
    await expect(tableInfo).toContainText('Showing');
    await expect(tableInfo).toContainText('entries');
    
    console.log('✅ Data table loaded successfully with data');
  });  
});
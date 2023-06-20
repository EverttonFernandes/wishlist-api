const request = require('supertest');
const app = "http://localhost:8080"

describe("Specification with responsibility to ensure the search for products", () => {
    it("should get resource not found when trying to get invalid product by id", async () => {
        const response = await request(app).get('/products'.concat("/123"))

        expect(response.status).toEqual(404)
        expect(response.body).toEqual({})
    })
})
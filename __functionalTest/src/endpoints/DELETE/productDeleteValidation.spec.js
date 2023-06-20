const request = require('supertest');
const app = "http://localhost:8080"

describe("Specification with responsibility to validate if the product to be excluded is invalid", () => {
    it("should get resource not found when trying delete invalid product by id", async () => {
        const response = await request(app).delete('/products'.concat("/123"))

        expect(response.status).toEqual(404)
        expect(response.body).toEqual({})
    })
})
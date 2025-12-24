# API Documentation - Giỏ Hàng và Đơn Hàng

## Base URL
```
http://localhost:8080/api
```

## 1. GIỎ HÀNG (CART)

### 1.1 Thêm sản phẩm vào giỏ hàng
**Endpoint:** `POST /cart/add`

**Parameters:**
- `userId` (query, required): ID của user
- `productId` (query, required): ID của sản phẩm
- `quantity` (query, optional, default: 1): Số lượng

**Request:**
```bash
curl -X POST "http://localhost:8080/api/cart/add?userId=1&productId=5&quantity=2"
```

**Response (200 OK):**
```json
{
  "id": 1,
  "productId": 5,
  "productName": "Whey Protein",
  "productDescription": "High quality protein",
  "productPrice": 50000,
  "productImage": "protein.jpg",
  "quantity": 2,
  "totalPrice": 100000
}
```

---

### 1.2 Lấy giỏ hàng của user
**Endpoint:** `GET /cart/{userId}`

**Parameters:**
- `userId` (path, required): ID của user

**Request:**
```bash
curl -X GET "http://localhost:8080/api/cart/1"
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "productId": 5,
    "productName": "Whey Protein",
    "productDescription": "High quality protein",
    "productPrice": 50000,
    "productImage": "protein.jpg",
    "quantity": 2,
    "totalPrice": 100000
  },
  {
    "id": 2,
    "productId": 10,
    "productName": "Creatine",
    "productDescription": "Pure creatine",
    "productPrice": 30000,
    "productImage": "creatine.jpg",
    "quantity": 1,
    "totalPrice": 30000
  }
]
```

---

### 1.3 Cập nhật số lượng sản phẩm
**Endpoint:** `PUT /cart/update/{cartItemId}`

**Parameters:**
- `cartItemId` (path, required): ID của item trong giỏ
- `quantity` (query, required): Số lượng mới

**Request:**
```bash
curl -X PUT "http://localhost:8080/api/cart/update/1?quantity=5"
```

**Response (200 OK):**
```json
{
  "id": 1,
  "productId": 5,
  "productName": "Whey Protein",
  "productDescription": "High quality protein",
  "productPrice": 50000,
  "productImage": "protein.jpg",
  "quantity": 5,
  "totalPrice": 250000
}
```

---

### 1.4 Xóa sản phẩm khỏi giỏ hàng (theo cartItemId)
**Endpoint:** `DELETE /cart/{cartItemId}`

**Parameters:**
- `cartItemId` (path, required): ID của item trong giỏ

**Request:**
```bash
curl -X DELETE "http://localhost:8080/api/cart/1"
```

**Response (200 OK):**
```json
{}
```

---

### 1.5 Xóa sản phẩm khỏi giỏ hàng (theo userId và productId)
**Endpoint:** `DELETE /cart/user/{userId}/product/{productId}`

**Parameters:**
- `userId` (path, required): ID của user
- `productId` (path, required): ID của sản phẩm

**Request:**
```bash
curl -X DELETE "http://localhost:8080/api/cart/user/1/product/5"
```

**Response (200 OK):**
```json
{}
```

---

### 1.6 Xóa toàn bộ giỏ hàng
**Endpoint:** `DELETE /cart/clear/{userId}`

**Parameters:**
- `userId` (path, required): ID của user

**Request:**
```bash
curl -X DELETE "http://localhost:8080/api/cart/clear/1"
```

**Response (200 OK):**
```json
{}
```

---

## 2. ĐƠN HÀNG (ORDER)

### 2.1 Tạo đơn hàng từ giỏ hàng
**Endpoint:** `POST /order/create/{userId}`

**Parameters:**
- `userId` (path, required): ID của user

**Request Body:**
```json
{
  "paymentMethod": "CREDIT_CARD",
  "shippingAddress": "123 Main Street, District 1, HCMC",
  "cartItemIds": [1, 2, 3]
}
```

**Request:**
```bash
curl -X POST "http://localhost:8080/api/order/create/1" \
  -H "Content-Type: application/json" \
  -d '{
    "paymentMethod": "CREDIT_CARD",
    "shippingAddress": "123 Main Street, District 1, HCMC",
    "cartItemIds": [1, 2, 3]
  }'
```

**Response (200 OK):**
```json
{
  "id": 1,
  "paymentMethod": "CREDIT_CARD",
  "status": "PENDING",
  "totalPrice": 130000,
  "shippingAddress": "123 Main Street, District 1, HCMC",
  "orderDate": "2025-12-24T10:30:00.000+00:00",
  "message": "Order created successfully"
}
```

**Response (400 Bad Request):**
```json
{
  "message": "Cart is empty"
}
```

**Response (404 Not Found):**
```json
{
  "message": "User not found"
}
```

---

### 2.2 Lấy danh sách đơn hàng của user
**Endpoint:** `GET /order/{userId}`

**Parameters:**
- `userId` (path, required): ID của user

**Request:**
```bash
curl -X GET "http://localhost:8080/api/order/1"
```

**Response (200 OK):**
```json
[
  {
    "order": {
      "id": 1,
      "paymentMethod": "CREDIT_CARD",
      "status": "PENDING",
      "totalPrice": 130000,
      "shippingAddress": "123 Main Street, District 1, HCMC",
      "orderDate": "2025-12-24T10:30:00.000+00:00"
    },
    "orderItems": [
      {
        "id": 1,
        "quantity": 2,
        "productId": 5,
        "productName": "Whey Protein",
        "productDescription": "High quality protein",
        "productPrice": 50000,
        "productImage": "protein.jpg"
      },
      {
        "id": 2,
        "quantity": 1,
        "productId": 10,
        "productName": "Creatine",
        "productDescription": "Pure creatine",
        "productPrice": 30000,
        "productImage": "creatine.jpg"
      }
    ]
  }
]
```

---

## 3. FRONTEND INTEGRATION EXAMPLE (React)

### 3.1 Thêm sản phẩm vào giỏ hàng
```javascript
const addToCart = async (userId, productId, quantity = 1) => {
  try {
    const response = await fetch(
      `http://localhost:8080/api/cart/add?userId=${userId}&productId=${productId}&quantity=${quantity}`,
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        }
      }
    );
    const data = await response.json();
    console.log('Added to cart:', data);
    return data;
  } catch (error) {
    console.error('Error adding to cart:', error);
  }
};
```

### 3.2 Lấy giỏ hàng
```javascript
const getCart = async (userId) => {
  try {
    const response = await fetch(
      `http://localhost:8080/api/cart/${userId}`
    );
    const data = await response.json();
    console.log('Cart items:', data);
    return data;
  } catch (error) {
    console.error('Error fetching cart:', error);
  }
};
```

### 3.3 Cập nhật số lượng
```javascript
const updateQuantity = async (cartItemId, newQuantity) => {
  try {
    const response = await fetch(
      `http://localhost:8080/api/cart/update/${cartItemId}?quantity=${newQuantity}`,
      {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        }
      }
    );
    const data = await response.json();
    console.log('Updated cart item:', data);
    return data;
  } catch (error) {
    console.error('Error updating quantity:', error);
  }
};
```

### 3.4 Xóa sản phẩm từ giỏ
```javascript
const removeFromCart = async (cartItemId) => {
  try {
    const response = await fetch(
      `http://localhost:8080/api/cart/${cartItemId}`,
      {
        method: 'DELETE'
      }
    );
    console.log('Removed from cart');
    return response.ok;
  } catch (error) {
    console.error('Error removing from cart:', error);
  }
};
```

### 3.5 Tạo đơn hàng
```javascript
const createOrder = async (userId, paymentMethod, shippingAddress, cartItemIds) => {
  try {
    const response = await fetch(
      `http://localhost:8080/api/order/create/${userId}`,
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          paymentMethod: paymentMethod,
          shippingAddress: shippingAddress,
          cartItemIds: cartItemIds
        })
      }
    );
    const data = await response.json();
    console.log('Order created:', data);
    return data;
  } catch (error) {
    console.error('Error creating order:', error);
  }
};
```

### 3.6 Lấy danh sách đơn hàng
```javascript
const getOrders = async (userId) => {
  try {
    const response = await fetch(
      `http://localhost:8080/api/order/${userId}`
    );
    const data = await response.json();
    console.log('User orders:', data);
    return data;
  } catch (error) {
    console.error('Error fetching orders:', error);
  }
};
```

---

## 4. STATUS CODES

| Code | Meaning |
|------|---------|
| 200  | Success |
| 400  | Bad Request (invalid data or out of stock) |
| 404  | Not Found (user, product, or cart item not found) |
| 500  | Internal Server Error |

---

## 5. NOTES

- Khi tạo đơn hàng thành công, giỏ hàng sẽ tự động được xóa
- Stock quantity của sản phẩm sẽ tự động giảm khi tạo đơn hàng
- Nếu số lượng sản phẩm trong giỏ vượt quá stock, API sẽ trả về lỗi 400
- CORS đã được cấu hình cho frontend tại `http://localhost:5173`

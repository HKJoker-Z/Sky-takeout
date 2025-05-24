# Sky Takeout - System Architecture
A distributed food delivery application built with Spring Boot, featuring order management, distributed locking, and real-time notifications.

## Core Components
### Order Management System
- Order Processing: Complete order lifecycle from submission to delivery OrderServiceImpl.java:67-139
- Payment Integration: Handles payment processing and status updates OrderServiceImpl.java:261-296
- Real-time Notifications: WebSocket integration for restaurant alerts OrderServiceImpl.java:286-294
### Distributed Locking
- Redis-based Locking: Prevents duplicate order submissions using Redis SETNX operations OrderServiceImpl.java:18
- Concurrency Control: Ensures data consistency across multiple application instances
## Technology Stack
- Backend: Spring Boot, MyBatis
- Database: MySQL with order and order_detail tables OrderMapper.xml:7-10
- Caching/Locking: Redis for distributed locks pom.xml:52-55
- Real-time Communication: WebSocket for notifications
- Payment: WeChat Pay integration
## Key Features
- Multi-tenant order processing
- Distributed lock mechanism for high-concurrency scenarios
- Real-time order status updates
- Comprehensive order lifecycle management
- Restaurant admin dashboard functionality
## Architecture Patterns
- Service Layer Pattern: Clear separation between controllers and business logic
- Repository Pattern: MyBatis mappers for data access
- Distributed Locking Pattern: Redis-based coordination for concurrent operations

### Wiki pages you might want to explore:
- Order Management ([HKJoker-Z/Sky-takeout](https://deepwiki.com/HKJoker-Z/Sky-takeout/2-order-management))
- Distributed Locking ([HKJoker-Z/Sky-takeout](https://deepwiki.com/HKJoker-Z/Sky-takeout/7.2-distributed-locking))

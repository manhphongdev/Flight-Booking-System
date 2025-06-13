package service.serviceimpl;

import dao.interfaces.ICouponDAO;
import dao.daoimpl.CouponDAOImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coupon;
import service.interfaces.ICouponService;

/**
 * @author Administrator
 * @version 1.1
 */
public class CouponServiceImpl implements ICouponService {

    private final ICouponDAO couponDAO;
    private static final Logger LOG = Logger.getLogger(CouponServiceImpl.class.getName());

    public CouponServiceImpl() {
        this.couponDAO = new CouponDAOImpl();
    }

    @Override
    public boolean save(Coupon coupon) {
        // Check if coupon code already exists
        if (!couponDAO.findByCode(coupon.getCouponCode()).isEmpty()) {
            throw new IllegalArgumentException("Coupon code already exists: " + coupon.getCouponCode());
        }
        Long insertedId = couponDAO.insert(coupon);
        if (insertedId == null) {
            throw new IllegalStateException("Failed to insert coupon with code: " + coupon.getCouponCode());
        }
        LOG.log(Level.INFO, "Coupon added with ID: {0}", insertedId);
        return true;
    }

    @Override
    public Coupon getByCode(String code) {
        List<Coupon> coupons = couponDAO.findByCode(code);
        return coupons.isEmpty() ? null : coupons.get(0);
    }

    @Override
    public List<Coupon> getAll() {
        return couponDAO.findAll();
    }

    @Override
    public boolean updateByCode(Coupon entity) {
        Coupon existingCoupon = getByCode(entity.getCouponCode());
        if (existingCoupon == null) {
            throw new IllegalArgumentException("Failed to update: Coupon code does not exist: " + entity.getCouponCode());
        }
        boolean isUpdated = couponDAO.updateByID(existingCoupon.getCouponId(), entity);
        if (!isUpdated) {
            throw new IllegalStateException("Failed to update coupon with code: " + entity.getCouponCode());
        }
        LOG.log(Level.INFO, "Coupon updated with code: {0}", entity.getCouponCode());
        return isUpdated;
    }

    @Override
    public boolean deleteByCode(String code) {
        Coupon existingCoupon = getByCode(code);
        if (existingCoupon == null) {
            throw new IllegalArgumentException("Failed to delete: Coupon code does not exist: " + code);
        }
        boolean isDeleted = couponDAO.deleteByID(existingCoupon.getCouponId());
        if (!isDeleted) {
            throw new IllegalStateException("Failed to delete coupon with code: " + code);
        }
        LOG.log(Level.INFO, "Coupon deleted with code: {0}", code);
        return isDeleted;
    }

    @Override
    public Coupon getByID(Long id) {
        return couponDAO.findByID(id).orElse(null);
    }

    @Override
    public boolean updateStatus(Long couponId, String status) {
        // Normalize status to lowercase to match JSP
        String normalizedStatus = status.toLowerCase();
        if (!normalizedStatus.equals("active") && !normalizedStatus.equals("inactive")) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        boolean isUpdated = couponDAO.updateStatus(couponId, normalizedStatus);
        if (!isUpdated) {
            throw new IllegalStateException("Failed to update status for coupon ID: " + couponId);
        }
        LOG.log(Level.INFO, "Coupon status updated for ID: {0} to {1}", new Object[]{couponId, normalizedStatus});
        return isUpdated;
    }

    public List<Coupon> getByCondition(String search, String status, String sortBy) {
        return couponDAO.selectByCondition(search, status, sortBy);
    }
}